package com.todo;

import java.time.LocalDateTime;

public class Task {
  private int id;
  private String description;
  private boolean isCompleted;
  private LocalDateTime dueDate;

  public Task(int id, String description, LocalDateTime dueDate) {
    this.id = id;
    this.description = description;
    this.isCompleted = false;
    this.dueDate = dueDate;
  }

  public int getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public boolean isCompleted() {
    return isCompleted;
  }

  public void setCompleted(boolean completed) {
    isCompleted = completed;
  }

  public LocalDateTime getDueDate() {
    return dueDate;
  }

  // Add this method to set the due date
  public void setDueDate(LocalDateTime dueDate) {
    this.dueDate = dueDate;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return String.format("Task #%d: %s (Due: %s) [%s]",
        id, description, dueDate.toString(), (isCompleted ? "Completed" : "Pending"));
  }
}
