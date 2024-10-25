package org.example.tz4krainet.Controllers;

import lombok.AllArgsConstructor;
import org.example.tz4krainet.DTO.ProjectDTO;
import org.example.tz4krainet.DTO.RecordDTO;
import org.example.tz4krainet.Service.ProjectService;
import org.example.tz4krainet.Service.RecordService;
import org.example.tz4krainet.Service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private final ProjectService projectService;
    private final RecordService recordService;
    private final UsersService usersService;

    // Создание проекта, проект может создавать только админ
    @PostMapping("/create/project")
    public ResponseEntity<?> create_project(@RequestBody ProjectDTO projectDTO){
        projectService.save_Project(projectDTO);
        return ResponseEntity.ok("fine");
    }
    // Создание записи, админ может создавать запись, если пользователь находиться в этой записи,то пользователь может ее изменять
    @PostMapping("/create/record")
    public ResponseEntity<?> create_time_tracker(@RequestBody RecordDTO  recordDTO){
        recordService.save_record_with(recordDTO);
        return  ResponseEntity.ok("fine");
    }
    // Find All
    @GetMapping("/findall/record")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(recordService.findAll());
    }
    //удаление записи
    @DeleteMapping("/delete/record")
    public ResponseEntity<?> delete_time_tracker(@RequestBody Integer timeRecordId){
        recordService.delete(timeRecordId);
        return  ResponseEntity.ok("fine");
    }
    @GetMapping("/findall/users")
    public ResponseEntity<?> findAllUsers(){
        return ResponseEntity.ok(usersService.findAll());
    }

}
