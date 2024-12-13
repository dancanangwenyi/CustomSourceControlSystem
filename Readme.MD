# Source Control System - README.md

## Overview

This project implements a simplified version of a source control system, similar to Git, in Java. It allows users to initialize a repository, stage files, commit changes, manage branches, and view logs. The system tracks changes in a file-based repository and offers basic functionality for version control.

## Features

- **Repository Initialization**: Creates a local repository with the necessary directories and files.
- **File Staging**: Allows files to be staged for committing.
- **Committing Changes**: Commits staged files with a commit message and logs the metadata.
- **Branch Management**: Supports creating new branches, switching branches, and logging commits for each branch.
- **Command-Line Interface**: Users interact with the system through command-line commands.

## Thinking Behind the Implementation

The main idea behind this implementation is to provide a simple, file-based version control system with basic functionality similar to Git. The design follows these principles:

1. **Modularity**: Each part of the system (repository management, staging, commits, branches) is encapsulated in separate classes (`Repository`, `Staging`, `Commit`, `BranchManager`) to make the system easier to maintain and extend.
2. **File System-Based**: Instead of using a database, the system uses the file system to manage repositories, commits, and staging areas. This keeps the implementation simple and intuitive, while still providing the core features of version control.
3. **Command-Line Interface**: The system is controlled through simple command-line arguments, making it easy to run from a terminal or script.

## Structure

The project consists of the following main classes:

1. **Repository**: Handles the initialization and management of the repository directories.
2. **Staging**: Manages the staging area where files are placed before being committed.
3. **Commit**: Handles committing staged changes with metadata.
4. **BranchManager**: Manages branches, including switching between them and logging commits.
5. **SourceControlSystem**: The entry point that processes command-line arguments and invokes the necessary operations.

## How to Run

### 1. **Compiling the Code**

To compile the Java source files, navigate to the root directory of the project and use the following command:

```bash
javac -d bin src/*.java

```
The -d bin flag specifies that the compiled .class files will be placed in the bin directory.
src/*.java compiles all the Java files in the src directory.

### 2. **Running the Source Control System**
   After compiling the code, you can run the SourceControlSystem class with the following command:

```bash
java -cp bin SourceControlSystem <command> [arguments]
```
<command>: The operation you want to perform (e.g., init, add, commit, log, branch, checkout).
[arguments]: Additional arguments required by the command (e.g., file path, branch name, commit message).

### 3. **Supported Commands**

#### 1. **Initializing the Repository**
   To initialize a new repository (create the .myvcs directory structure):

```bash
java -cp bin SourceControlSystem init
```
This command creates the repository directories and the necessary files (branches.txt and HEAD).

#### 2. **Staging a File**
   To add a file to the staging area:

```bash
java -cp bin SourceControlSystem add <file-path>
```
<file-path>: Path to the file you want to stage.
Example: java -cp bin SourceControlSystem add myfile.txt
This command copies the file into the staging area for the next commit.

#### 3. **Committing Changes**
   To commit the staged changes with a commit message:

```bash
java -cp bin SourceControlSystem commit <commit-message>
```
<commit-message>: A message describing the commit.
Example: java -cp bin SourceControlSystem commit "Initial commit"
This command commits the staged files and records the commit in the repository, including the branch name and commit message.

#### 4. **Viewing the Commit Log**
   To view the commit history:

```bash
java -cp bin SourceControlSystem log
```
This command shows all commits in the repository with metadata (e.g., branch and commit message).

#### 5. **Creating a New Branch**
   To create a new branch:

```bash
java -cp bin SourceControlSystem branch <branch-name>
```
<branch-name>: Name of the new branch.
```bash
Example: java -cp bin SourceControlSystem branch feature-xyz
```
This command creates a new branch and appends it to the branches.txt file.

#### 6. **Switching Branches**
   To switch to an existing branch:

```bash
java -cp bin SourceControlSystem checkout <branch-name>
```
<branch-name>: Name of the branch to switch to.
Example: java -cp bin SourceControlSystem checkout feature-xyz
This command updates the HEAD file to point to the new branch.

##How the System Works
**Initialization:** When the repository is initialized (init), the system creates a .myvcs directory with the following structure:

***staging/:*** A directory where staged files are placed before committing.

***commits/:*** A directory where commit history is stored.

***branches.txt:*** A file that lists all the branches in the repository.

***HEAD:*** A file that points to the current branch.

***Staging:*** When a file is added (add), it's copied from the working directory to the staging area (.myvcs/staging). Staging prepares files for committing.

**Committing:** When commit is executed, all staged files are copied into a new directory inside the commits folder. A metadata file is created with information about the commit, including the branch name and commit message. The staging area is then cleared.

**Branching:** Branches are stored in branches.txt. The system supports switching between branches using the checkout command. Each commit is linked to a branch, and the HEAD file points to the current branch.

##Example Workflow
Initialize a repository:

```bash
java -cp bin SourceControlSystem init
```
Create a new file and add it to staging:

```bash
echo "Hello World" > hello.txt
java -cp bin SourceControlSystem add hello.txt
```
Commit the changes:

```bash
java -cp bin SourceControlSystem commit "First commit"
```
Create a new branch:

```bash
java -cp bin SourceControlSystem branch feature-xyz
```
Switch to the new branch:

```bash
java -cp bin SourceControlSystem checkout feature-xyz
```
View the commit log:

```bash
java -cp bin SourceControlSystem log
```
#Conclusion
This implementation provides a simple and extensible command-line version control system that can be used to manage files, track changes, and work with branches. It follows core principles of source control and can be enhanced with more features, such as merging branches or handling conflicts.

