package org.example.tz4krainet.Service;

import lombok.AllArgsConstructor;
import org.example.tz4krainet.DTO.ChangeProfileDTO;
import org.example.tz4krainet.DTO.ChangeProjectDTO;
import org.example.tz4krainet.DTO.ProjectDTO;
import org.example.tz4krainet.Exception.PasswordIncorrectException;
import org.example.tz4krainet.Exception.ProjectNotFoundException;
import org.example.tz4krainet.Exception.ProjectWasFoundException;
import org.example.tz4krainet.Mappers.ProjectMapper;
import org.example.tz4krainet.Models.Project;
import org.example.tz4krainet.Models.Users;
import org.example.tz4krainet.Repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public void save_Project(ProjectDTO projectDTO){
        Optional<Project> projectOptional= projectRepository.findByProjectName(projectDTO.getProjectName());
        if(!projectOptional.isEmpty())
        {
            throw new ProjectWasFoundException(projectDTO.getProjectName());
        }
        Project project=projectMapper.projectDTOTOproject(projectDTO);
        project.setStartDate(new Date());
        projectRepository.save(project);
    }
    public List<Project> find_all(){
        return projectRepository.findAll();
    }

    public Optional<Project> findById(Integer projectId) {
        return projectRepository.findById(projectId);
    }

    public List<Project> findAll() {
        return  projectRepository.findAll();
    }
    public void change(ChangeProjectDTO u) {
        Project project=projectRepository.findByProjectName(u.getOldProjectName()).orElseThrow(()-> new ProjectNotFoundException(u.getProjectName()));
        if(projectRepository.findByProjectName(u.getProjectName()).isEmpty()){
            project.setProjectName(u.getProjectName());
            if(u.getDescription()!=null){
                project.setDescription(u.getDescription());
            }
            projectRepository.save(project);
        }
        else{
            throw new ProjectWasFoundException(u.getProjectName());
        }
    }

    public void delete(String project_name) {
        Project project=projectRepository.findByProjectName(project_name).orElseThrow(()->new ProjectNotFoundException(project_name));
        projectRepository.delete(project);
    }
}
