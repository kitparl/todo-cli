package com.todo;

import picocli.CommandLine;

public class App {
  public static void main(String[] args) {
    try {
      int exitCode = new CommandLine(new ToDoCLI()).execute(args);
      ToDoCLI.getManager().saveTasks(); // Ensure we have access to the ToDoManager for saving tasks
      System.exit(exitCode);
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
  }
}
