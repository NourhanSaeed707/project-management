package com.example.ProjectManagementSystem.service.Impl;
import com.example.ProjectManagementSystem.exception.NotFoundException;
import com.example.ProjectManagementSystem.modal.Issue;
import com.example.ProjectManagementSystem.request.IssueRequest;
import com.example.ProjectManagementSystem.respository.IssueRepository;
import com.example.ProjectManagementSystem.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IssueServiceImpl implements IssueService {
    @Autowired
    private IssueRepository issueRepository;

    @Override
    public Issue getIssueById(Long issueId) throws Exception {
        return  issueRepository.findById(issueId).orElseThrow(() -> new NotFoundException("Issue not found"));
    }

    @Override
    public List<Issue> getIssueByProjectId(Long projectId) throws Exception {
        return issueRepository.findByProjectId(projectId).orElseThrow(() -> new NotFoundException("Issue not found"));
    }

    @Override
    public Issue create(IssueRequest issue, Long userId) throws Exception {
        return null;
    }

    @Override
    public String deleteIssue(Long issueId, Long userId) throws Exception {
        return "";
    }

    @Override
    public Issue addUserToIssue(Long issueId, Long userId) throws Exception {
        return null;
    }

    @Override
    public Issue updateStatus(Long issueId, String status) throws Exception {
        return null;
    }
}
