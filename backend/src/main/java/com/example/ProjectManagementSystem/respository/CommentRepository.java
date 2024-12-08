package com.example.ProjectManagementSystem.respository;
import com.example.ProjectManagementSystem.modal.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface CommentRepository extends JpaRepository<Comments, Long> {
    Optional<List<Comments>> findByIssueId(Long issueId);
}
