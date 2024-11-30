package com.example.ProjectManagementSystem.service;
import com.example.ProjectManagementSystem.modal.Chat;
import com.example.ProjectManagementSystem.modal.Project;
import com.example.ProjectManagementSystem.modal.User;

import java.util.*;

public interface ProjectService {

    Project create(Project project, User user) throws Exception;
    List<Project> getProjectByTeam(User user, String category, String tag) throws Exception;
    Project getProjectById(Long projectId) throws Exception;
    void delete(Long projectId, Long userId) throws Exception;
    Project update(Project updateProject, Long projectId) throws Exception;
    void addUserToProject(Long projectId, Long userId) throws Exception;
    void removeUserToProject(Long projectId, Long userId) throws Exception;
    Chat getChatByProjectId(Long projectId) throws Exception;
    List<Project> searchProjects(String keyword, User user) throws Exception;

}
