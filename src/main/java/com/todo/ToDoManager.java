package com.todo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ToDoManager {
  private List<Task> tasks = new ArrayList<>();
  private int taskIdCounter = 1;
  private final String filename;

  private static final Gson gson = new GsonBuilder()
      .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
      .setPrettyPrinting()
      .create();

  public ToDoManager(String filename) {
    this.filename = filename;
    initializeTasksFile(); // Ensure the file is initialized
    loadTasks(); // Load tasks from the file
  }

  private void initializeTasksFile() {
    File file = new File(filename);

    // Check if the parent directory is null, create it if necessary
    File parentDir = file.getParentFile();
    if (parentDir != null) {
      parentDir.mkdirs(); // Create directories if they don't exist
    } else {
      System.err.println("Warning: The parent directory of the tasks file is null.");
    }

    // Create the tasks file if it doesn't exist
    if (!file.exists()) {
      try {
        file.createNewFile(); // Create the file
        // Initialize with an empty JSON array
        try (FileWriter writer = new FileWriter(file)) {
          writer.write("[]");
        }
      } catch (IOException e) {
        System.err.println("Error initializing tasks file: " + e.getMessage());
      }
    }
  }

  public Task addTask(String description, LocalDateTime dueDate) {
    Task task = new Task(taskIdCounter++, description, dueDate);
    tasks.add(task);
    saveTasks();
    return task;
  }

  public boolean deleteTask(int id) {
    boolean removed = tasks.removeIf(task -> task.getId() == id);
    if (removed) {
      saveTasks();
    }
    return removed;
  }

  public boolean editTask(int id, String newDescription, LocalDateTime newDueDate) {
    Optional<Task> task = tasks.stream().filter(t -> t.getId() == id).findFirst();
    if (task.isPresent()) {
      if (newDescription != null) {
        task.get().setDescription(newDescription);
      }
      if (newDueDate != null) {
        task.get().setDueDate(newDueDate);
      }
      saveTasks();
      return true;
    }
    return false;
  }

  public boolean markTaskCompleted(int id) {
    Optional<Task> task = tasks.stream().filter(t -> t.getId() == id).findFirst();
    if (task.isPresent()) {
      task.get().setCompleted(true);
      saveTasks();
      return true;
    }
    return false;
  }

  public List<Task> listTasks(boolean showCompleted) {
    if (showCompleted) {
      return new ArrayList<>(tasks);
    }
    return tasks.stream()
        .filter(task -> !task.isCompleted())
        .toList();
  }

  public void saveTasks() {
    try (FileWriter writer = new FileWriter(filename)) {
      gson.toJson(tasks, writer);
    } catch (IOException e) {
      System.err.println("Error saving tasks: " + e.getMessage());
    }
  }

  private void loadTasks() {
    try (FileReader reader = new FileReader(filename)) {
      Type taskListType = new TypeToken<ArrayList<Task>>() {
      }.getType();
      List<Task> loadedTasks = gson.fromJson(reader, taskListType);
      if (loadedTasks != null) {
        tasks = loadedTasks;
        taskIdCounter = tasks.stream()
            .mapToInt(Task::getId)
            .max()
            .orElse(0) + 1; // Increment counter to ensure unique IDs
      }
    } catch (IOException e) {
      System.err.println("Error loading tasks: " + e.getMessage());
      tasks = new ArrayList<>(); // Reset to empty list if load fails
    } catch (com.google.gson.JsonSyntaxException e) {
      System.err.println("Error parsing tasks from JSON: " + e.getMessage());
      tasks = new ArrayList<>(); // Reset to empty list if parsing fails
    }
  }
}
