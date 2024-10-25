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
    // Сервис для сохранение проекта
    public void save_Project(ProjectDTO projectDTO){
        // Проверка есть ли проект с именем в системе, если есть выкидывает ошибку
        Optional<Project> projectOptional= projectRepository.findByProjectName(projectDTO.getProjectName());
        if(!projectOptional.isEmpty())
        {
            throw new ProjectWasFoundException(projectDTO.getProjectName());
        }
        // Если нет то сохраняет в базу
        Project project=projectMapper.projectDTOTOproject(projectDTO);
        project.setStartDate(new Date());
        projectRepository.save(project);
    }

    // Ищет все проекты
    public List<Project> find_all(){
        return projectRepository.findAll();
    }
    // Ищет проект по id
    public Optional<Project> findById(Integer projectId) {
        return projectRepository.findById(projectId);
    }
    // Изменяет проект
    public void change(ChangeProjectDTO u) {

        //Ищет по имени проекта, если нету то неправильно введенные данные
        Project project=projectRepository.findByProjectName(u.getOldProjectName()).orElseThrow(()->
                new ProjectNotFoundException(u.getProjectName()));
        // Ищет по новому имени проекта, если его нет в системее то продолжает работу(сохранеят)
        // Если есть Выкидывает ошибку
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
    // удаляет проект по имени, если имя не найдено, то выкидывает ошибку
    public void delete(String project_name) {
        Project project=projectRepository.findByProjectName(project_name).orElseThrow(()->new ProjectNotFoundException(project_name));
        projectRepository.delete(project);
    }
}
