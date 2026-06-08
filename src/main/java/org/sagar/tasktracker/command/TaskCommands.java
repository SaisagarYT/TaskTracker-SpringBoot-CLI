package org.sagar.tasktracker.command;

import org.springframework.shell.core.command.annotation.Command;
import org.springframework.shell.core.command.annotation.CommandGroup;
import org.springframework.shell.core.command.annotation.Option;

@CommandGroup
public class TaskCommands {

    @Command(name = "greet", description = "greeting to user..")
    public String greet(@Option(longName = "name", shortName = 'n', defaultValue = "User") String name){
        return "Hello" + " " + name + " ! Welcome to TaskTracker.";
    }
}
