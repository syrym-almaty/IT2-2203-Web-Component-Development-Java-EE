# Web Component Development with Java EE

---

## Welcome to the IT2-2205 Web Component Development project! Follow the instructions below to clone the repository, set up your environment, and start developing

---

## Cloning the Repository

> To get started, clone the repository using the command below:

```bash

git clone git@github.com:syrym-almaty/IT2-2205-Web-Component-Development-Java-EE.git
```

## Navigate into the project directory

```bash

cd IT2-2205-Web-Component-Development-Java-EE
```

> Build and Run the Application
> You can build and run the application with Docker using the following command:

```bash

docker build -t my-spring-app . && docker run -p 8080:8080 my-spring-app
```

## Alternatively, you can use Docker Compose

```bash

docker-compose up --build

```

> Working with Branches
> Create a New Branch
> To create a new branch for your work, use:

```bash

git checkout -b student-username

```

## Making Changes

> Check the status of your changes:

```bash

git status

```

## Stage and Commit Your Changes

> Stage all changes you wish to commit:

```bash

git add .

```

> Then commit your changes with a descriptive message:

```bash

git commit -m "Your detailed commit message here"

```

## Push Changes to Your Branch

> Push your changes to the remote repository:

```bash

git push origin student-username

```

## Keeping Your Branch Updated

> Switch to the Main Branch
> To switch back to the main branch, use:

```bash

git checkout main

```

## Pull the Latest Changes

> Fetch the latest changes from the main branch:

```bash

git pull origin main

```

## Merge Changes into Your Branch

> Switch back to your branch:

```bash

git checkout student-username

```

> Then merge the latest changes from the main branch into your branch:

```bash

git merge main

```

## testing the Application

`Swagger UI`

> To test the application endpoints, visit:

```curl

http://localhost:8080/swagger-ui.html

```

`Swagger UI`

## H2 Database Console

> Open the H2 console at:

```curl

http://localhost:8080/h2-console

```

`H2 Console`

> Credentials:

**Driver Class**: org.h2.Driver
**JDBC URL**: jdbc:h2:file:./data/demo-db
**User Name**: sa
**Password**: password

## Running SQL Commands

> You can run SQL commands in the H2 console, such as:

```sql

SHOW TABLES;
SELECT * FROM STUDENT;
```

## 1. Setting Remote URL

```bash

git remote set-url origin git@github.com:syrym-almaty/IT2-2205-Web-Component-Development-Java-EE.git

```

> **Description****: Sets the remote repository URL for the origin remote to the specified GitHub repository.

## 2. Checking Remote URLs

```bash

git remote -v

```

 **Description**: Displays the current remote URLs associated with the repository.

## 3. Adding a Remote

```bash

git remote add origin git@github.com:syrym-almaty/IT2-2205-Web-Component-Development-Java-EE.git

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

---

> ## GitHub SSH Configuration Guide

---

> ## This document outlines the steps to set up SSH for GitHub and automate your workflow using the command line.

---

## SSH Configuration for GitHub

---

## 1. Check Git Configuration

> Ensure your Git configuration is set up correctly:

```bash

git config --global --list

```

## 2. Set Up Your User Information

> Configure your username and email (replace with demo credentials if needed):

```bash

git config --global user.name "yourusername"
git config --global user.email "youremail@example.com"

```

## 3. Generate SSH Key

> Generate an SSH key for authentication:

```bash

ssh-keygen -t ed25519 -C "youremail@example.com"

```

Use a secure passphrase when prompted.

## 4. Start the SSH Agent

> Run the SSH agent in the background:

```bash

eval "$(ssh-agent -s)"

```

## 5. Add Your SSH Key

> Add your SSH private key to the SSH agent:

```bash

ssh-add ~/.ssh/id_ed25519

```

## 6. Verify the SSH Key

> List the keys added to the SSH agent:

```bash

ssh-add -l

```

## 7. Copy Your Public Key

> Copy your SSH public key to add it to GitHub:

```bash

cat ~/.ssh/id_ed25519.pub

```

Then visit GitHub SSH Keys to add it.

> Alternatively, you can copy it directly to the clipboard:

```bash

clip < ~/.ssh/id_ed25519.pub

```

## 8. test SSH Connection

> test your SSH connection to GitHub:

```bash

ssh -T git@github.com

```

You should see a message indicating successful authentication.

## 9. Set Remote Repository URL

> If you encounter issues pushing data, set the remote URL:

```bash

git remote set-url origin git@github.com:yourusername/your-repository.git

```

## 10. Push and Pull Changes

> Push your changes to the remote repository:

```bash

git push --set-upstream origin master

```

> You can also push changes to specific branches:

```bash

git push origin test

```

> To pull updates from the remote repository:

```bash

git pull origin test

```

> If necessary, force push to a branch:

```bash

git push --force origin test

```

Creating a Repository from the Terminal
> To create a new GitHub repository directly from your terminal, follow these steps:

> Navigate to your project directory:

```bash

cd /path/to/your/project

```

> Initialize a new Git repository:

```bash

git init

```

> Stage and commit your files:

```bash

git add .
git commit -m "Initial commit"

```

> Add the remote repository:

```bash

git remote add origin git@github.com:yourusername/your-repository.git

```

> Push to the remote repository:

```bash

git push -u origin master

```

The -u flag sets the upstream tracking for future pushes and pulls.

Using GitHub CLI on Ubuntu 20
> To manage GitHub repositories easily, install the GitHub CLI:

```bash

sudo apt install gh

```

Log in to your GitHub account:

```bash

gh auth login

```

List your repositories:

```bash

gh repo list

```

Automate Your Bash Configuration
> You can automate your .bashrc file to streamline your workflow. Open the file for editing:

```bash

nano ~/.bashrc

```

Add Configuration
> Add the following lines to your .bashrc file:

```bash

# Add your scripts directory to the PATH
export PATH=$PATH:/c/Users/yourName/scripts

# Alias for creating GitHub repositories easily
alias create-repo='python /c/Users/yourName/scripts/create_repo.py'

# Aliases for listing repositories
alias listreposhttps='curl -H "Authorization: token YOUR_PAT" https://api.github.com/user/repos?per_page=100 | jq -r ".[].clone_url"'
alias listreposssh='curl -H "Authorization: token YOUR_PAT" https://api.github.com/user/repos?per_page=100 | jq -r ".[].ssh_url"'

# Automatically source the .bashrc file on each new session
alias reloadbash='source ~/.bashrc'
alias editbash='nano ~/.bashrc && source ~/.bashrc'

```

Reload Your Configuration
> After saving changes, reload your .bashrc file:

```bash

source ~/.bashrc

```

---
