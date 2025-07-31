package com.taskmanagement.service;

import com.taskmanagement.dto.TaskDTO;
import com.taskmanagement.entity.Task;
import com.taskmanagement.entity.TaskPriority;
import com.taskmanagement.entity.TaskStatus;
import com.taskmanagement.mapper.TaskMapper;
import com.taskmanagement.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;
    
    @Autowired
    private TaskMapper taskMapper;
    
    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    public Optional<TaskDTO> getTaskById(Long id) {
        return taskRepository.findById(id)
                .map(taskMapper::toDTO);
    }
    
    public TaskDTO createTask(TaskDTO taskDTO) {
        Task task = taskMapper.toEntity(taskDTO);
        if (task.getStatus() == null) {
            task.setStatus(TaskStatus.TODO);
        }
        if (task.getPriority() == null) {
            task.setPriority(TaskPriority.MEDIUM);
        }
        Task savedTask = taskRepository.save(task);
        return taskMapper.toDTO(savedTask);
    }
    
    public Optional<TaskDTO> updateTask(Long id, TaskDTO taskDTO) {
        return taskRepository.findById(id)
                .map(existingTask -> {
                    taskMapper.updateEntityFromDTO(taskDTO, existingTask);
                    Task updatedTask = taskRepository.save(existingTask);
                    return taskMapper.toDTO(updatedTask);
                });
    }
    
    public boolean deleteTask(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    public List<TaskDTO> getTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status)
                .stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    public List<TaskDTO> getTasksByPriority(TaskPriority priority) {
        return taskRepository.findByPriority(priority)
                .stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    public List<TaskDTO> searchTasks(String keyword) {
        return taskRepository.findByTitleOrDescriptionContaining(keyword)
                .stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    public List<TaskDTO> getTasksSortedByCreatedDate() {
        return taskRepository.findByOrderByCreatedAtDesc()
                .stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    public List<TaskDTO> getTasksSortedByDueDate() {
        return taskRepository.findByOrderByDueDateAsc()
                .stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    public List<TaskDTO> getOverdueTasks() {
        return taskRepository.findOverdueTasks(LocalDateTime.now())
                .stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    public Optional<TaskDTO> updateTaskStatus(Long id, TaskStatus status) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setStatus(status);
                    Task updatedTask = taskRepository.save(task);
                    return taskMapper.toDTO(updatedTask);
                });
    }
}