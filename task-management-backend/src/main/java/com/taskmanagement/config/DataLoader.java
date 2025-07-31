package com.taskmanagement.config;

import com.taskmanagement.entity.Task;
import com.taskmanagement.entity.TaskPriority;
import com.taskmanagement.entity.TaskStatus;
import com.taskmanagement.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {
    
    @Autowired
    private TaskRepository taskRepository;
    
    @Override
    public void run(String... args) throws Exception {
        if (taskRepository.count() == 0) {
            loadSampleData();
        }
    }
    
    private void loadSampleData() {
        Task task1 = new Task();
        task1.setTitle("Complete project documentation");
        task1.setDescription("Write comprehensive documentation for the task management system");
        task1.setStatus(TaskStatus.TODO);
        task1.setPriority(TaskPriority.HIGH);
        task1.setDueDate(LocalDateTime.now().plusDays(3));
        
        Task task2 = new Task();
        task2.setTitle("Code review for frontend");
        task2.setDescription("Review Angular components and provide feedback");
        task2.setStatus(TaskStatus.IN_PROGRESS);
        task2.setPriority(TaskPriority.MEDIUM);
        task2.setDueDate(LocalDateTime.now().plusDays(1));
        
        Task task3 = new Task();
        task3.setTitle("Setup CI/CD pipeline");
        task3.setDescription("Configure automated deployment pipeline");
        task3.setStatus(TaskStatus.TODO);
        task3.setPriority(TaskPriority.LOW);
        task3.setDueDate(LocalDateTime.now().plusWeeks(1));
        
        Task task4 = new Task();
        task4.setTitle("Fix login bug");
        task4.setDescription("Resolve authentication issue in the login component");
        task4.setStatus(TaskStatus.COMPLETED);
        task4.setPriority(TaskPriority.HIGH);
        task4.setDueDate(LocalDateTime.now().minusDays(1));
        
        Task task5 = new Task();
        task5.setTitle("Database optimization");
        task5.setDescription("Optimize database queries for better performance");
        task5.setStatus(TaskStatus.TODO);
        task5.setPriority(TaskPriority.MEDIUM);
        task5.setDueDate(LocalDateTime.now().plusDays(5));
        
        taskRepository.save(task1);
        taskRepository.save(task2);
        taskRepository.save(task3);
        taskRepository.save(task4);
        taskRepository.save(task5);
        
        System.out.println("Sample data loaded successfully!");
    }
}