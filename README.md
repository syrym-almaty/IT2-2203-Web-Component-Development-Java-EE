# Web Component Development with Java EE

---

## Welcome to the IT2-2203 Web Component Development project! Follow the instructions below to clone the repository, set up your environment, and start developing

---

## Cloning the Repository

> To get started, clone the repository using the command below:

```bash

git clone git@github.com:syrym-almaty/IT2-2203-Web-Component-Development-Java-EE.git
```

## Navigate into the project directory

```bash

cd IT2-2203-Web-Component-Development-Java-EE
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

## Testing the Application

`Swagger UI`

> To test the application endpoints, visit:

`Swagger UI`

## H2 Database Console

> Open the H2 console at:

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

git remote set-url origin git@github.com:syrym-almaty/IT2-2203-Web-Component-Development-Java-EE.git
```
> **Description****: Sets the remote repository URL for the origin remote to the specified GitHub repository.

## 2. Checking Remote URLs
```bash

git remote -v
```
 **Description**: Displays the current remote URLs associated with the repository.

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
