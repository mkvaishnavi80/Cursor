package com.taskmanagement.controller;

import com.taskmanagement.dto.TaskDTO;
import com.taskmanagement.entity.TaskPriority;
import com.taskmanagement.entity.TaskStatus;
import com.taskmanagement.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {
    
    @Autowired
    private TaskService taskService;
    
    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        List<TaskDTO> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
        Optional<TaskDTO> task = taskService.getTaskById(id);
        return task.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@Valid @RequestBody TaskDTO taskDTO) {
        TaskDTO createdTask = taskService.createTask(taskDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @Valid @RequestBody TaskDTO taskDTO) {
        Optional<TaskDTO> updatedTask = taskService.updateTask(id, taskDTO);
        return updatedTask.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        boolean deleted = taskService.deleteTask(id);
        return deleted ? ResponseEntity.noContent().build() 
                       : ResponseEntity.notFound().build();
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<TaskDTO>> getTasksByStatus(@PathVariable TaskStatus status) {
        List<TaskDTO> tasks = taskService.getTasksByStatus(status);
        return ResponseEntity.ok(tasks);
    }
    
    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<TaskDTO>> getTasksByPriority(@PathVariable TaskPriority priority) {
        List<TaskDTO> tasks = taskService.getTasksByPriority(priority);
        return ResponseEntity.ok(tasks);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<TaskDTO>> searchTasks(@RequestParam String keyword) {
        List<TaskDTO> tasks = taskService.searchTasks(keyword);
        return ResponseEntity.ok(tasks);
    }
    
    @GetMapping("/sorted/created")
    public ResponseEntity<List<TaskDTO>> getTasksSortedByCreatedDate() {
        List<TaskDTO> tasks = taskService.getTasksSortedByCreatedDate();
        return ResponseEntity.ok(tasks);
    }
    
    @GetMapping("/sorted/due-date")
    public ResponseEntity<List<TaskDTO>> getTasksSortedByDueDate() {
        List<TaskDTO> tasks = taskService.getTasksSortedByDueDate();
        return ResponseEntity.ok(tasks);
    }
    
    @GetMapping("/overdue")
    public ResponseEntity<List<TaskDTO>> getOverdueTasks() {
        List<TaskDTO> tasks = taskService.getOverdueTasks();
        return ResponseEntity.ok(tasks);
    }
    
    @PatchMapping("/{id}/status")
    public ResponseEntity<TaskDTO> updateTaskStatus(@PathVariable Long id, @RequestBody TaskStatus status) {
        Optional<TaskDTO> updatedTask = taskService.updateTaskStatus(id, status);
        return updatedTask.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
    }
}