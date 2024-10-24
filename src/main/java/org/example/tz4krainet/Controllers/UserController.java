package org.example.tz4krainet.Controllers;

import lombok.AllArgsConstructor;
import org.example.tz4krainet.DTO.ChangeProfileDTO;
import org.example.tz4krainet.DTO.ChangeRecordDTO;
import org.example.tz4krainet.DTO.UsersDTO;
import org.example.tz4krainet.Service.RecordService;
import org.example.tz4krainet.Service.UsersService;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private UsersService usersService;
    private RecordService recordService;
    //Если у пользователя есть
    @PatchMapping("/change/profile")
    public ResponseEntity<?> changeProfile(@RequestBody ChangeProfileDTO u, Authentication authentication){
        Integer id= Integer.valueOf(authentication.getName());
        usersService.change(u,id);
        return ResponseEntity.ok("profile changed");
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProfile(Authentication authentication,@RequestBody ChangeProfileDTO password){
        Integer id= Integer.valueOf(authentication.getName());
        usersService.delete(id,password.getPassword());
        return ResponseEntity.ok("user deleted");
    }
    @PatchMapping("/change/record")
    public ResponseEntity<?> changeRecord(@RequestBody ChangeRecordDTO changeRecordDTO,Authentication authentication){
        Integer id= Integer.valueOf(authentication.getName());
        recordService.changeRecord(changeRecordDTO,id);
        return ResponseEntity.ok("profile changed");
    }
    @GetMapping("/find/myprojects")
    public ResponseEntity<?> allMyprojects(Authentication authentication){
        Integer id= Integer.valueOf(authentication.getName());
        return ResponseEntity.ok(recordService.findByUserId(id));
    }
}
