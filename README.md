# IT2-2203-Web-Component-Development-Java-EE

## Overview

Welcome to the repository for the Web Component Development Lab. In this project, each student will work on their own branch to contribute and learn the process of version control and collaboration using Git and GitHub. The main branch is protected and can only be updated by the instructor.



### Guidelines for Collaborators

- **Main branch protection**: Only the instructor can make changes to the `main` branch.
- **Student branches**: Each student must create and work on their own branch, named after their GitHub username (e.g., `student-username`).
- **Feature development**: All changes should be made in your individual branch. Once completed, changes can be pushed and reviewed.

## Download Git for windows

https://git-scm.com/downloads

## Instructions for Students

### 1. Cloning the Repository

First, clone the repository to your local machine using the following command:

```bash
git clone git@github.com:syrym-almaty/IT2-2203-Web-Component-Development-Java-EE.git

cd IT2-2203-Web-Component-Development-Java-EE

```
```bash
# Build and Run with One Command where the Dockerfile is located

docker build -t my-spring-app . && docker run -p 8080:8080 my-spring-app

# Command to Run

docker-compose up --build

```
```bash
# Create a new branch
git checkout -b student-username

# Making Changes

git status

# Stage the files
git add .

# Commit your changes
git commit -m "Your detailed commit message here"

# Push changes to your branch
git push origin student-username

# Switch to main branch
git checkout main

# Pull the latest changes from the main branch
git pull origin main

# Switch back to your branch
git checkout student-username

# Merge the latest main branch changes into your branch
git merge main

```

```bash

# we have added swagger
# click on try out and test end-points

http://localhost:8080/swagger-ui.html




# Open the H2 console at

http://localhost:8080/h2-console

# Credentials

Driver Class: org.h2.Driver
JDBC URL: jdbc:h2:file:./data/demo-db
User Name: sa
Password: password

# run sql commands in http://localhost:8080/h2-console

SHOW TABLES;
SELECT * FROM STUDENT;
```

## 1. Setting Remote URL

```bash

git remote set-url origin git@github.com:syrym-almaty/IT2-2203-Web-Component-Development-Java-EE.git
```
**> **Description****: Sets the remote repository URL for the origin remote to the specified GitHub repository.

## 2. Checking Remote URLs
```bash

git remote -v
```
> **Description**: Displays the current remote URLs associated with the repository.

## 3. Adding a Remote
```bash

git remote add origin git@github.com:syrym-almaty/IT2-2203-Web-Component-Development-Java-EE.git
```
> **Description**: Adds a new remote named origin pointing to the specified GitHub repository.

## 4. Switching Branches
```bash

git checkout main
```
> **Description**: Switches to the main branch in your local repository.

## 5. Staging Changes
```bash

git add .
```
> **Description**: Stages all changes in the current directory for the next commit.

## 6. Committing Changes
```bash

git commit -m "Your commit message here"
```
> **Description**: Commits the staged changes with a descriptive message.

## 7. Force Pushing Changes
```bash

git push origin main --force
```
> **Description**: Forcefully pushes your local changes to the main branch on the remote repository, overwriting any existing changes.

## 8. Pushing with Safety
```bash

git push origin main --force-with-lease
```
> **Description**: Forcefully pushes your local changes while ensuring that the remote branch hasn’t been updated since your last pull.

## 9. Fetching Remote Changes
```bash

git fetch origin
```
> **Description**: Fetches the latest changes from the remote repository without merging them into your local branch.

## 10. Hard Resetting
```bash

git reset --hard HEAD
```
> **Description**: Resets your current branch to the last commit, discarding any changes in the working directory.

## 11. Creating a New Branch
```bash
git checkout -b my-feature-branch
```
> **Description**: Creates a new branch named my-feature-branch and switches to it.

## 12. Pushing a New Branch
```bash

git push origin my-feature-branch
```
> **Description**: Pushes the newly created branch to the remote repository.

## 13. Merging Changes
```bash

git merge main
```
> **Description**: Merges changes from the main branch into your current branch.

## 14. Viewing Commit History
```bash

git log
```
> **Description**: Displays the commit history for the current branch.

## 15. Resolving Merge Conflicts
```bash

<<<<<<< HEAD
// Your local changes
=======
// Changes from the main branch
>>>>>>> main
```
> **Description**: Markers used to indicate merge conflicts in the code; manual resolution is required.

## 16. Using a Merge Tool
```bash

git mergetool
```
> **Description**: Launches a configured merge tool to help resolve merge conflicts.

## 17. Rebasing
```bash

git rebase origin/main
```
> **Description**: Applies changes from your current branch on top of the latest changes from the main branch.

## 18. Viewing Remote Branches
```bash

git branch -r
```
> **Description**: Lists all remote branches.

## 19. Pulling Changes
```bash

git pull origin main
```
> **Description**: Fetches and merges changes from the main branch of the remote repository into your current branch.

## 20. Updating a Local Branch
```bash

git pull --rebase
```
> **Description**: Fetches changes from the remote branch and applies your local commits on top of the fetched commits.
