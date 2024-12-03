package com.example.ProjectManagementSystem.service.Impl;
import com.example.ProjectManagementSystem.DTO.IssueDTO;
import com.example.ProjectManagementSystem.exception.NotFoundException;
import com.example.ProjectManagementSystem.modal.Issue;
import com.example.ProjectManagementSystem.modal.Project;
import com.example.ProjectManagementSystem.modal.User;
import com.example.ProjectManagementSystem.request.IssueRequest;
import com.example.ProjectManagementSystem.respository.IssueRepository;
import com.example.ProjectManagementSystem.service.IssueService;
import com.example.ProjectManagementSystem.service.ProjectService;
import com.example.ProjectManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import java.util.*;


@Service
public class IssueServiceImpl implements IssueService {
    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Issue getIssueById(Long issueId) throws Exception {
        return  issueRepository.findById(issueId).orElseThrow(() -> new NotFoundException("Issue not found"));
    }

    @Override
    public List<Issue> getIssueByProjectId(Long projectId) throws Exception {
        return issueRepository.findByProjectId(projectId).orElseThrow(() -> new NotFoundException("Issue not found"));
    }

    @Override
    public IssueDTO create(IssueRequest issueBody, User user) throws Exception {
        Issue issue = issueFields(issueBody, user);
        IssueDTO issueDTO = modelMapper.map(issue, IssueDTO.class);
        issueRepository.save(issue);
        return issueDTO;
    }

    @Override
    public void deleteIssue(Long issueId, Long userId) throws Exception {
        issueRepository.deleteById(issueId);
    }

    @Override
    public Issue addUserToIssue(Long issueId, Long userId) throws Exception {
        User user = userService.findUserById(userId);
        Issue issue = getIssueById(issueId);
        issue.setAssignee(user);
        return issueRepository.save(issue);
    }

    @Override
    public Issue updateStatus(Long issueId, String status) throws Exception {
        Issue issue = getIssueById(issueId);
        if(issue == null) {
            throw new NotFoundException("Issue not found");
        }
        issue.setStatus(status);
        return issueRepository.save(issue);
    }

    private Issue issueFields(IssueRequest issueBody, User user ) throws Exception {
        Project project = projectService.getProjectById(issueBody.getProjectId());
        if(project == null) {
            throw new NotFoundException("Project not found");
        }
        return Issue.builder().title(issueBody.getTitle()).description(issueBody.getDescription()).status(issueBody.getStatus())
                .projectId(issueBody.getProjectId())
                .priority(issueBody.getPriority())
                .dueDate(issueBody.getDueDate())
                .project(project)
                .build();

    }

}
