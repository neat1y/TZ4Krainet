package org.example.tz4krainet.Repository;

import org.example.tz4krainet.Models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Integer> {
    Optional<Project> findByProjectName(String name);
}
