package com.example.ProjectManagementSystem.controller;
import com.example.ProjectManagementSystem.DTO.IssueDTO;
import com.example.ProjectManagementSystem.modal.Issue;
import com.example.ProjectManagementSystem.modal.User;
import com.example.ProjectManagementSystem.request.IssueRequest;
import com.example.ProjectManagementSystem.response.AuthResponse;
import com.example.ProjectManagementSystem.response.MessageResponse;
import com.example.ProjectManagementSystem.service.IssueService;
import com.example.ProjectManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issue")
public class IssueController {
    @Autowired
    private IssueService issueService;
    @Autowired
    private UserService userService;

    @GetMapping("/{issueId}")
    public ResponseEntity<Issue> getIssueById(@PathVariable Long issueId) throws Exception {
        return ResponseEntity.ok(issueService.getIssueById(issueId));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Issue>> getIssueByProjectId(@PathVariable Long projectId) throws Exception {
        return ResponseEntity.ok(issueService.getIssueByProjectId(projectId));
    }

    @PostMapping("/")
    public ResponseEntity<IssueDTO> create(@RequestBody IssueRequest issueRequest, @RequestHeader("Authorization") String token) throws Exception {
       User tokenUser = userService.findUserProfileByJwt(token);
       User user = userService.findUserById(tokenUser.getId());
       return ResponseEntity.ok(issueService.create(issueRequest, tokenUser));
    }

    @DeleteMapping("/{issueId}")
    public ResponseEntity<MessageResponse> delete(@PathVariable Long issueId, @RequestHeader("Authorization") String token) throws Exception {
        User tokenUser = userService.findUserProfileByJwt(token);
        issueService.deleteIssue(issueId, tokenUser.getId());
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("Issue Deleted");
        return ResponseEntity.ok(messageResponse);
    }

    @PutMapping("/{issueId}/assignee/{userId}")
    public ResponseEntity<Issue> addUserToIssue(@PathVariable Long issueId, @PathVariable Long userId) throws Exception {
        Issue issue = issueService.addUserToIssue(issueId, userId);
        return ResponseEntity.ok(issue);
    }

    @PutMapping("/{issueId}/status/{status}")
    public ResponseEntity<Issue> updateIssueStatus(@PathVariable String status, @PathVariable Long issueId) throws Exception {
        return ResponseEntity.ok(issueService.updateStatus(issueId, status));
    }



}
