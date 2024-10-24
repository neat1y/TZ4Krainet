package org.example.tz4krainet.Mappers;

import javax.annotation.processing.Generated;
import org.example.tz4krainet.DTO.ProjectDTO;
import org.example.tz4krainet.Models.Project;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-24T09:02:50+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Arch Linux)"
)
@Component
public class ProjectMapperImpl implements ProjectMapper {

    @Override
    public ProjectDTO projectTOprojectDTO(Project project) {
        if ( project == null ) {
            return null;
        }

        ProjectDTO projectDTO = new ProjectDTO();

        projectDTO.setProjectName( project.getProjectName() );
        projectDTO.setDescription( project.getDescription() );

        return projectDTO;
    }

    @Override
    public Project projectDTOTOproject(ProjectDTO projectDTO) {
        if ( projectDTO == null ) {
            return null;
        }

        Project project = new Project();

        project.setProjectName( projectDTO.getProjectName() );
        project.setDescription( projectDTO.getDescription() );

        return project;
    }
}
