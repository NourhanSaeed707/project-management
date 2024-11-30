package com.example.ProjectManagementSystem.service.Impl;
import com.example.ProjectManagementSystem.exception.ProjectNotFound;
import com.example.ProjectManagementSystem.exception.UserNotFoundException;
import com.example.ProjectManagementSystem.modal.Chat;
import com.example.ProjectManagementSystem.modal.Project;
import com.example.ProjectManagementSystem.modal.User;
import com.example.ProjectManagementSystem.respository.ProjectRepository;
import com.example.ProjectManagementSystem.respository.UserRepository;
import com.example.ProjectManagementSystem.service.ChatService;
import com.example.ProjectManagementSystem.service.ProjectService;
import com.example.ProjectManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ChatService chatService;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Project create(Project project, User user) throws Exception {
        Project createdProject = createProjectFields(project, user);
        project.getTeam().add(user);
        Project savedProject = projectRepository.save(createdProject);
        Chat chat = Chat.builder().project(savedProject).build();
        Chat projectChat = chatService.create(chat);
        savedProject.setChat(projectChat);
        return savedProject;
    }

    @Override
    public List<Project> getProjectByTeam(User user, String category, String tag) throws Exception {
        List<Project> projects = projectRepository.findByTeamContainingOrOwner(user, user);
        if(category != null) {
            projects = projects.stream().filter(project -> project.getCategory().equals(category)).toList();
        }
        if(tag != null) {
            projects = projects.stream().filter(project -> project.getTags().contains(tag)).toList();
        }
        return projects;
    }

    @Override
    public Project getProjectById(Long projectId) throws Exception {
        return projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFound("Project not found"));
    }

    @Override
    public void delete(Long projectId, Long userId) throws Exception {
         projectRepository.deleteById(projectId);
    }

    @Override
    public Project update(Project updateProject, Long projectId) throws Exception {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFound("Project not found"));
        project.setName(updateProject.getName());
        project.setDescription(updateProject.getDescription());
        project.setTags(updateProject.getTags());
        return projectRepository.save(project);
    }

    @Override
    public void addUserToProject(Long projectId, Long userId) throws Exception {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFound("Project not found"));
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User not found"));
        if(!project.getTeam().contains(user)) {
            project.getChat().getUsers().add(user);
            project.getTeam().add(user);
        }
        projectRepository.save(project);
    }

    @Override
    public void removeUserToProject(Long projectId, Long userId) throws Exception {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFound("Project not found"));
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User not found"));
        if(project.getTeam().contains(user)) {
            project.getChat().getUsers().remove(user);
            project.getTeam().remove(user);
        }
        projectRepository.save(project);
    }

    @Override
    public Chat getChatByProjectId(Long projectId) throws Exception {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFound("Project not found"));
        return project.getChat();
    }

    @Override
    public List<Project> searchProjects(String keyword, User user) throws Exception {
        return projectRepository.findByNameContainingAndTeamContains(keyword, user);
    }

    private Project createProjectFields(Project project, User user) {
        return Project.builder().owner(user).tags(project.getTags()).name(project.getName())
                .category(project.getCategory())
                .description(project.getDescription()).build();
    }
}
