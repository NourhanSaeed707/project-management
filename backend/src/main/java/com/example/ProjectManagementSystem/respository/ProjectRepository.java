package com.example.ProjectManagementSystem.respository;
import com.example.ProjectManagementSystem.modal.Project;
import com.example.ProjectManagementSystem.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByNameContainingAndTeamContains(String partialName, User user);
    List<Project> findByTeamContainingOrOwner(User user, User owner);
}
