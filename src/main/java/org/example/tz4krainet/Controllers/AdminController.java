package org.example.tz4krainet.Controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.tz4krainet.DTO.ProjectDTO;
import org.example.tz4krainet.DTO.RecordDTO;
import org.example.tz4krainet.DTO.UsersDTO;
import org.example.tz4krainet.Mappers.ProjectMapper;
import org.example.tz4krainet.Models.TimeRecordId;
import org.example.tz4krainet.Service.ProjectService;
import org.example.tz4krainet.Service.RecordService;
import org.example.tz4krainet.Service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
//    private final UsersService usersService;
    // Если админ может создавать пользователя, то можно использовать метод который сейчас в коментарии
//    @GetMapping("/create/users")
//    public ResponseEntity<?> create_users(@RequestBody UsersDTO dto){
//        usersService.save(dto);
//        return ResponseEntity.ok("fine");
//    }
    private final ProjectService projectService;
    private final RecordService recordService;
    @PostMapping("/create/project")
    public ResponseEntity<?> create_project(@RequestBody ProjectDTO projectDTO){
        projectService.save_Project(projectDTO);
        return ResponseEntity.ok("fine");
    }
    @PostMapping("/create/record")
    public ResponseEntity<?> create_time_tracker(@RequestBody RecordDTO  recordDTO){
        recordService.save_record_with(recordDTO);
        return  ResponseEntity.ok("fine");
    }
    @DeleteMapping("/delete/record")
    public ResponseEntity<?> delete_time_tracker(@RequestBody TimeRecordId timeRecordId){
        recordService.delete(timeRecordId);
        return  ResponseEntity.ok("fine");
    }
}
