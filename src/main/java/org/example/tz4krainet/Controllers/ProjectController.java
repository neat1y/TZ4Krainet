package org.example.tz4krainet.Controllers;

import lombok.AllArgsConstructor;
import org.example.tz4krainet.DTO.ChangeProfileDTO;
import org.example.tz4krainet.DTO.ChangeProjectDTO;
import org.example.tz4krainet.Models.Project;
import org.example.tz4krainet.Service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
@AllArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    @GetMapping("/all")
    public ResponseEntity<List<Project>> all_project(){
        return  ResponseEntity.ok(projectService.find_all());
    }
    @PatchMapping("/change/profile")
    public ResponseEntity<?> changeProfile(@RequestBody ChangeProjectDTO u){
        projectService.change(u);
        return ResponseEntity.ok("profile changed");
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProfile(@RequestBody ChangeProjectDTO projectDTO){
        projectService.delete(projectDTO.getProjectName());
        return ResponseEntity.ok("user deleted");
    }
}
