package com.example.ProjectManagementSystem.controller;
import com.example.ProjectManagementSystem.DTO.CommentsReqDTO;
import com.example.ProjectManagementSystem.modal.Comments;
import com.example.ProjectManagementSystem.modal.User;
import com.example.ProjectManagementSystem.response.MessageResponse;
import com.example.ProjectManagementSystem.service.CommentService;
import com.example.ProjectManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<Comments> create(@RequestBody CommentsReqDTO commentsReqDTO, @RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findUserProfileByJwt(token);
        Comments createdComment = commentService.create(commentsReqDTO.getIssueId(), user.getId(), commentsReqDTO.getContent());
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<MessageResponse> delete(@PathVariable Long commentId, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        commentService.deleteComment(commentId, user.getId());
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("Comment deleted successfully");
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @GetMapping("/{issueId}")
    public ResponseEntity<List<Comments>> getCommentsByIssueId(@PathVariable Long issueId) {
        List<Comments> comments = commentService.findCommentByIssueId(issueId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
