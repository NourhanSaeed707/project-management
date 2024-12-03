package com.example.ProjectManagementSystem.service;
import com.example.ProjectManagementSystem.modal.Comments;
import java.util.*;

public interface CommentService {
    Comments create(Long issueId, Long userId, String content);
    void deleteComment(Long commentId, Long userId) throws Exception;
    List<Comments> findCommentByIssueId(Long issueId);

}
