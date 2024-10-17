# Still in Development

## Java TODO CLI Tool 🗒️

A command-line interface (CLI) application built in Java that helps you manage your daily tasks efficiently through terminal commands. Stay organized and boost your productivity with this lightweight task management tool.

## Features ✨
<!---
- Create, read, update, and delete tasks
- Mark tasks as complete/incomplete
- List all tasks with their status
- Filter tasks by status (completed/pending)
- Priority-based task management
- Simple and intuitive command-line interface
- Data persistence across sessions
-->
## Installation 🚀

### Linux Installation

1. Download the File Using curl
```bash
curl -L -o todo-cli-1.0-SNAPSHOT.jar https://raw.githubusercontent.com/kitparl/todo-cli/main/target/todo-cli-1.0-SNAPSHOT.jar
```

2.  Move the JAR File to /usr/local/bin
```bash
sudo mv todo-cli-1.0-SNAPSHOT.jar /usr/local/bin/
```

3. Make the JAR File Executable
```bash
sudo chmod +x /usr/local/bin/todo-cli-1.0-SNAPSHOT.jar
```

4. Add an Alias
  1. Open the `.bashrc` or `.zshrc` file
for `bash` users
  ```bash
  nano ~/.bashrc
  ```
Or for `zsh` users:
  ```bash
  nano ~/.bashrc
  ```

  2. Add this line
  ```bash
  alias todo='java -jar /usr/local/bin/todo-cli-1.0-SNAPSHOT.jar'
  ```

  3. Save the file and then reload the configuration
  ```bash
  source ~/.bashrc    # for bash users
  source ~/.zshrc      # for zsh users
  ```
   
### Prerequisites

- Java JDK 21 or higher
- Maven (for building the project)

<!---
### Steps

1. Clone the repository

```bash
git clone https://github.com/yourusername/todo-cli.git
cd todo-cli
```

2. Build the project

```bash
mvn clean package
```

3. Create an alias (Optional)

```bash
# For Unix/Linux/macOS (add to ~/.bashrc or ~/.zshrc)
alias todo="java -jar /path/to/todo-cli.jar"

# For Windows (create a batch file)
@echo off
java -jar C:\path\to\todo-cli.jar %*
```

## Usage 📖

### Basic Commands

```bash
# Add a new task
todo add "Complete README documentation"

# List all tasks
todo list

# Mark task as complete
todo complete <task-id>

# Remove a task
todo remove <task-id>

# Show task details
todo show <task-id>

# Filter completed tasks
todo list --completed

# Filter pending tasks
todo list --pending
```

### Advanced Commands

```bash
# Add task with priority
todo add "Important meeting" --priority high

# Add task with due date
todo add "Submit report" --due "2024-10-20"

# List tasks by priority
todo list --priority high

# Export tasks to CSV
todo export tasks.csv
```

## Configuration ⚙️

The application stores its data in:

- Unix/Linux/macOS: `~/.todo-cli/tasks.json`
- Windows: `%APPDATA%\todo-cli\tasks.json`

You can modify the storage location by creating a `config.properties` file in the same directory as the JAR file:

```properties
data.directory=/custom/path/to/storage
```
-->
## Contributing 🤝

Contributions are welcome! Please feel free to submit a Pull Request. For major changes, please open an issue first to discuss what you would like to change.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License 📝

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments 👏

- Inspired by various CLI tools in the productivity space
- Built with love for the command-line enthusiasts

## Support 🆘

If you encounter any issues or have questions, please:

1. Check the [Issues](https://github.com/yourusername/todo-cli/issues) page
2. Create a new issue if your problem isn't already listed
3. Provide as much detail as possible in bug reports

---

Made with ❤️ by [Pranshu Singh Bisht]

_Note: Remember to star ⭐ this repository if you find it helpful!_
