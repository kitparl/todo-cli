package com.todo;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.concurrent.Callable;

@Command(name = "todo", description = "CLI ToDo Manager", mixinStandardHelpOptions = true, version = "ToDo CLI 1.0", subcommands = {
    ToDoCLI.AddCommand.class, ToDoCLI.ListCommand.class, ToDoCLI.CompleteCommand.class,
    ToDoCLI.DeleteCommand.class, ToDoCLI.EditCommand.class })
public class ToDoCLI implements Callable<Integer> {
  private static final String TASKS_FILE = "tasks.json";
  private static final ToDoManager manager = new ToDoManager(TASKS_FILE);
  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

  @Override
  public Integer call() {
    CommandLine.usage(this, System.out);
    return 0;
  }

  public static ToDoManager getManager() {
    return manager;
  }

  @Command(name = "add", description = "Add a new task")
  static class AddCommand implements Callable<Integer> {
    @Parameters(paramLabel = "<description>", description = "Description of the task")
    private String description;

    @Option(names = { "--due" }, description = "Due date in format 'yyyy-MM-dd HH:mm'")
    private String dueDateString;

    @Override
    public Integer call() {
      LocalDateTime dueDate = null;
      if (dueDateString != null && !dueDateString.isEmpty()) {
        try {
          dueDate = LocalDateTime.parse(dueDateString, formatter);
        } catch (DateTimeParseException e) {
          System.out.println("Invalid date format. Please use 'yyyy-MM-dd HH:mm'.");
          return 1;
        }
      }
      Task task = manager.addTask(description, dueDate);
      System.out.printf("Added task: %s%n", task);
      return 0;
    }
  }

  @Command(name = "list", description = "List all tasks")
  static class ListCommand implements Callable<Integer> {
    @Option(names = { "--all" }, description = "Show completed tasks")
    private boolean showCompleted;

    @Override
    public Integer call() {
      List<Task> tasks = manager.listTasks(showCompleted);
      if (tasks.isEmpty()) {
        System.out.println("No tasks found.");
      } else {
        tasks.forEach(System.out::println);
      }
      return 0;
    }
  }

  @Command(name = "complete", description = "Mark a task as completed")
  static class CompleteCommand implements Callable<Integer> {
    @Parameters(paramLabel = "<id>", description = "Task ID")
    private int id;

    @Override
    public Integer call() {
      if (manager.markTaskCompleted(id)) {
        System.out.println("Task marked as completed.");
        return 0;
      }
      System.out.println("Task not found.");
      return 1;
    }
  }

  @Command(name = "delete", description = "Delete a task")
  static class DeleteCommand implements Callable<Integer> {
    @Parameters(paramLabel = "<id>", description = "Task ID")
    private int id;

    @Override
    public Integer call() {
      if (manager.deleteTask(id)) {
        System.out.println("Task deleted successfully.");
        return 0;
      }
      System.out.println("Task not found.");
      return 1;
    }
  }

  @Command(name = "edit", description = "Edit a task")
  static class EditCommand implements Callable<Integer> {
    @Parameters(paramLabel = "<id>", description = "Task ID")
    private int id;

    @Option(names = { "--description" }, description = "New description")
    private String newDescription;

    @Option(names = { "--due" }, description = "New due date (yyyy-MM-dd HH:mm)")
    private String newDueDateString;

    @Override
    public Integer call() {
      LocalDateTime newDueDate = null;
      if (newDueDateString != null && !newDueDateString.isEmpty()) {
        try {
          newDueDate = LocalDateTime.parse(newDueDateString, formatter);
        } catch (DateTimeParseException e) {
          System.out.println("Invalid date format. Please use 'yyyy-MM-dd HH:mm'.");
          return 1;
        }
      }
      if (manager.editTask(id, newDescription, newDueDate)) {
        System.out.println("Task updated successfully.");
        return 0;
      }
      System.out.println("Task not found.");
      return 1;
    }
  }
}
