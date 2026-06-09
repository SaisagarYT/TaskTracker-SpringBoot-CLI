package org.sagar.tasktracker.controller;

import org.sagar.tasktracker.model.Task;
import org.sagar.tasktracker.repository.TaskRepository;
import org.sagar.tasktracker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    TaskService taskService;

    @PostMapping("/add")
    public ResponseEntity<Task> addTask (@RequestBody String t){
        Task task = taskService.addService(t);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }
}
