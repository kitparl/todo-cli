package com.todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

public class ToDoManager {
  private List<Task> tasks = new ArrayList<>();
  private int taskIdCounter = 1;

  public Task addTask(String description, LocalDateTime dueDate) {
    Task task = new Task(taskIdCounter++, description, dueDate);
    tasks.add(task);
    return task;
  }

  public boolean deleteTask(int id) {
    return tasks.removeIf(task -> task.getId() == id);
  }

  public boolean editTask(int id, String newDescription, LocalDateTime newDueDate) {
    Optional<Task> task = tasks.stream().filter(t -> t.getId() == id).findFirst();
    if (task.isPresent()) {
      task.get().setDescription(newDescription);
      if (newDueDate != null) {
        task.get().setDueDate(newDueDate);
      }
      return true;
    }
    return false;
  }

  public boolean markTaskCompleted(int id) {
    Optional<Task> task = tasks.stream().filter(t -> t.getId() == id).findFirst();
    if (task.isPresent()) {
      task.get().setCompleted(true);
      return true;
    }
    return false;
  }

  public List<Task> listTasks(boolean showCompleted) {
    if (showCompleted) {
      return tasks;
    }
    return tasks.stream().filter(task -> !task.isCompleted()).toList();
  }
}
