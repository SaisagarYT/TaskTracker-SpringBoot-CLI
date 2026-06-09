package org.sagar.tasktracker.command;

import org.hibernate.mapping.Any;
import org.sagar.tasktracker.model.Task;
import org.sagar.tasktracker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.command.annotation.Command;
import org.springframework.shell.core.command.annotation.CommandGroup;
import org.springframework.shell.core.command.annotation.Option;

import java.util.List;
import java.util.Objects;

@CommandGroup
public class TaskCommands {

    @Autowired
    private TaskService taskService;

    @Command(name = "greet", description = "greeting to user..")
    public String greet(@Option(longName = "name", shortName = 'n', defaultValue = "Unknown User") String name){
        return "Hello" + " " + name + " ! Welcome to TaskTracker.";
    }

    @Command(name = "task")
    public String addTask(@Option(longName = "add", shortName = 'a', defaultValue = "Nothing added !")String task){
        taskService.addService(task);
        return "Task added successfully...";
    }

    @Command(name = "task-list")
    public String showTask(@Option(longName = "show", shortName = 's', defaultValue = "Nothing to display!")String task){
        List<Task> tasks = taskService.displayService();
        StringBuilder output = new StringBuilder();
        if(tasks.isEmpty()){
            return "There are no tasks!";
        }
        if(Objects.equals(task, "first")){
            return String.format("| ID: %-5d | Task: %s | ",tasks.getFirst().getId(),tasks.getFirst().getTask() + (tasks.getFirst().getIsComplete()? "(Done ✓)":"(Progress ↻)\n"));
        }
        if(Objects.equals(task,"last")){
            return String.format("| ID: %-5d | Task: %s | ",tasks.getLast().getId(),tasks.getLast().getTask()+ (tasks.getFirst().getIsComplete()? "(Done ✓)":"(Progress ↻)\n"));
        }
        for (Task t : tasks) {
            output.append(String.format("| ID: %-5d | Task: %s | %s\n", t.getId(), t.getTask(), (t.getIsComplete()? "(Done ✓)":"(Progress ↻)")));
        }
        return output.toString();
    }

    @Command(name = "task-remove")
    public String removeTask(@Option(longName = "remove", shortName = 'r', defaultValue = "Nothing removed !")Long taskId){
        taskService.deleteService(taskId);
        return "Task removed successfully..." + "\n" + "Id: "+taskId;
    }

    @Command(name = "task-status")
    public String updateStatus(@Option(longName = "mark", shortName = 'm', defaultValue = "Nothing removed !")Long taskId){
        taskService.updateStatusService(taskId);
        return "Task status updated successfully..." + "\n" + "Id: "+taskId;
    }
}
