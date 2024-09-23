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

```bash
# Switch to an existing branch:


git checkout <branch_name>

git switch <branch_name>

git checkout main


# Create and switch to a new branch:


git checkout -b <new_branch_name>

git switch -c <new_branch_name>

git checkout -b feature/new-feature


# AFTER GIT PULL COMMAND merge conflicts how to solve them
<<<<<<< HEAD
// Your local changes
=======
 // Changes from the main branch
>>>>>>> main
```

```bash

# Rebasing Instead of Merging

## Step 1: Ensure you're on your local branch (e.g., feature/new-feature):

git checkout feature/new-feature

## Step 2: Fetch the latest changes from the main branch:

git fetch origin

## Step 3: Rebase your branch onto the latest main:

git rebase origin/main

## Step 4: If there are conflicts, Git will pause and let you resolve them manually. After resolving them, use:

git add <conflicted_file>
git rebase --continue

## Step 5: Once the rebase is done, push your changes:

git push --force-with-lease

```
```bash
# Resolve Conflicts Without Manual Deletion

## Use a Merge Tool

### Launch a merge tool:

git mergetool

## Automatic Conflict Resolution with ours or theirs

### Use ours strategy (to keep your local changes):

git merge -X ours main

### Use theirs strategy (to keep the changes from main):

git merge -X theirs main

# Best Practices to Avoid Future Merge Conflicts

## Pull Frequently from Main

git checkout main
git pull origin main
git checkout feature/new-feature
git merge main

# Make Smaller, Isolated Changes

## Keeping your changes small and isolated to specific parts of the codebase helps reduce conflicts, as the more you modify the same areas, the higher the chance of conflicts.

# Communicate with the Team

## If you and your team are working on the same files, communication is key. Avoid editing the same files simultaneously unless necessary.

# Using git rerere (Reuse Recorded Resolutions)

## Enable rerere:

git config --global rerere.enabled true

# When Merging Cannot Be Avoided

## Git will mark the conflicting files with the conflict markers (<<<<<<, ======, >>>>>>).

## You will need to resolve conflicts manually by choosing the correct version of the code.

## After resolving the conflicts, you must:
git add <conflicted_file>
git commit

# Key Commands:

# Rebasing Workflow (Preferred):

# Ensure you are on the feature branch
git checkout feature/new-feature

# Fetch the latest changes from the remote repository
git fetch origin

# Rebase your branch on top of main
git rebase origin/main

# Resolve any conflicts, then:
git add <conflicted_file>
git rebase --continue

# Push the changes (with force to avoid conflicts)
git push --force-with-lease


# Merging Workflow (If Needed):
# Ensure you are on the feature branch
git checkout feature/new-feature

# Pull changes from main and merge
git pull origin main

# Resolve any conflicts manually
git add <conflicted_file>
git commit
```

```bash
# Push the changes
git push origin feature/new-feature
```
