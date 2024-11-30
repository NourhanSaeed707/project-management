package com.example.ProjectManagementSystem.respository;
import com.example.ProjectManagementSystem.modal.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    Optional<List<Issue>> findByProjectId(Long id);
}
