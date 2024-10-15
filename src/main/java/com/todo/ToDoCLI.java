package com.todo;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Callable;

@Command(name = "todo", description = "CLI ToDo Manager", mixinStandardHelpOptions = true, version = "ToDo CLI 1.0")
public class ToDoCLI implements Callable<Integer> {

  private static final ToDoManager manager = new ToDoManager();
  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

  @Command(name = "add", description = "Add a new task")
  public void addTask(
      @Parameters(paramLabel = "<description>", description = "Task description") String description,
      @Option(names = { "-d", "--due" }, description = "Due date (format: yyyy-MM-dd HH:mm)") String dueDate) {
    LocalDateTime due = dueDate != null ? LocalDateTime.parse(dueDate, formatter) : LocalDateTime.now().plusDays(1);
    Task task = manager.addTask(description, due);
    System.out.printf("Added: %s%n", task);
  }

  @Command(name = "delete", description = "Delete a task")
  public void deleteTask(
      @Parameters(paramLabel = "<id>", description = "Task ID") int id) {
    if (manager.deleteTask(id)) {
      System.out.println("Task deleted.");
    } else {
      System.out.println("Task not found.");
    }
  }

  @Command(name = "edit", description = "Edit an existing task")
  public void editTask(
      @Parameters(paramLabel = "<id>", description = "Task ID") int id,
      @Option(names = { "-d", "--description" }, description = "New description") String description,
      @Option(names = { "-due",
          "--due-date" }, description = "New due date (format: yyyy-MM-dd HH:mm)") String dueDate) {
    LocalDateTime newDueDate = dueDate != null ? LocalDateTime.parse(dueDate, formatter) : null;
    if (manager.editTask(id, description, newDueDate)) {
      System.out.println("Task edited successfully.");
    } else {
      System.out.println("Task not found.");
    }
  }

  @Command(name = "list", description = "List all tasks")
  public void listTasks(
      @Option(names = { "-a", "--all" }, description = "Show all tasks including completed") boolean showAll) {
    manager.listTasks(showAll).forEach(System.out::println);
  }

  @Command(name = "complete", description = "Mark a task as completed")
  public void completeTask(
      @Parameters(paramLabel = "<id>", description = "Task ID") int id) {
    if (manager.markTaskCompleted(id)) {
      System.out.println("Task marked as completed.");
    } else {
      System.out.println("Task not found.");
    }
  }

  @Override
  public Integer call() {
    CommandLine.usage(this, System.out);
    return 0;
  }

  public static void main(String[] args) {
    int exitCode = new CommandLine(new ToDoCLI()).execute(args);
    System.exit(exitCode);
  }
}
