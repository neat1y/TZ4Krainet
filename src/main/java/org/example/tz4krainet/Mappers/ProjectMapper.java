package org.example.tz4krainet.Mappers;


import org.example.tz4krainet.DTO.ProjectDTO;
import org.example.tz4krainet.DTO.UsersDTO;
import org.example.tz4krainet.Models.Project;
import org.example.tz4krainet.Models.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
//    @Mapping(target = "project_name", source = "project_name")
//    @Mapping(target = "description", source = "description")
    ProjectDTO projectTOprojectDTO(Project project);
    @Mapping(target = "projectName", source = "projectName")
    @Mapping(target = "description", source = "description")
    Project projectDTOTOproject(ProjectDTO projectDTO);

}
