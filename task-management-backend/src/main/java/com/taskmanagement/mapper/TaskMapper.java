package com.taskmanagement.mapper;

import com.taskmanagement.dto.TaskDTO;
import com.taskmanagement.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    
    public TaskDTO toDTO(Task task) {
        if (task == null) {
            return null;
        }
        
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        dto.setPriority(task.getPriority());
        dto.setDueDate(task.getDueDate());
        dto.setCreatedAt(task.getCreatedAt());
        dto.setUpdatedAt(task.getUpdatedAt());
        
        return dto;
    }
    
    public Task toEntity(TaskDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Task task = new Task();
        task.setId(dto.getId());
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setPriority(dto.getPriority());
        task.setDueDate(dto.getDueDate());
        
        return task;
    }
    
    public void updateEntityFromDTO(TaskDTO dto, Task task) {
        if (dto == null || task == null) {
            return;
        }
        
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setPriority(dto.getPriority());
        task.setDueDate(dto.getDueDate());
    }
}