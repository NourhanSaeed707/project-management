package com.example.ProjectManagementSystem.service.Impl;
import com.example.ProjectManagementSystem.exception.NotFoundException;
import com.example.ProjectManagementSystem.modal.Comments;
import com.example.ProjectManagementSystem.modal.Issue;
import com.example.ProjectManagementSystem.modal.User;
import com.example.ProjectManagementSystem.respository.CommentRepository;
import com.example.ProjectManagementSystem.respository.IssueRepository;
import com.example.ProjectManagementSystem.respository.UserRepository;
import com.example.ProjectManagementSystem.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Comments create(Long issueId, Long userId, String content) {
        Issue issue = issueRepository.findById(issueId).orElseThrow(() -> new NotFoundException("Issue not found"));
        User user =  userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        Comments comments = Comments.builder().issue(issue).user(user).createdDate(LocalDateTime.now()).content(content).build();
        Comments savedComment = commentRepository.save(comments);
        issue.getComments().add(savedComment);
        return savedComment;
    }

    @Override
    public void deleteComment(Long commentId, Long userId) {

    }

    @Override
    public List<Comments> findCommentByIssueId(Long issueId) {
        return List.of();
    }
}
