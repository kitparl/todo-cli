package com.todo;

import java.time.LocalDateTime;

public class Task {
  private int id;
  private String description;
  private LocalDateTime dueDate;
  private boolean completed;

  public Task(int id, String description, LocalDateTime dueDate) {
    this.id = id;
    this.description = description;
    this.dueDate = dueDate;
    this.completed = false;
  }

  public int getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public LocalDateTime getDueDate() {
    return dueDate;
  }

  public boolean isCompleted() {
    return completed;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setDueDate(LocalDateTime dueDate) {
    this.dueDate = dueDate;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  @Override
  public String toString() {
    return String.format("Task{id=%d, description='%s', dueDate=%s, completed=%s}",
        id, description, dueDate, completed);
  }
}
