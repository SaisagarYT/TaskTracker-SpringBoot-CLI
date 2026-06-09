package org.sagar.tasktracker.service;

import org.sagar.tasktracker.model.Task;
import org.sagar.tasktracker.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    public Task addService(String t){
        Task task = new Task();
        task.setTask(t);
        return taskRepository.save(task);
    }

    public List<Task> displayService(){
        return (List<Task>) taskRepository.findAll();
    }

    public void deleteService(Long id){
        taskRepository.deleteById(id);
    }

    public void updateStatusService(Long id){
        Optional<Task> task = taskRepository.findById(id);
        if(task.isPresent()){
            Task temp = task.get();
            temp.setIsComplete(true);
            taskRepository.save(temp);
        }
    }
}
