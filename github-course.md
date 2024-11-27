# GitHub Full Course

## GitHub Intro

### Files and Folders

#### Files

There are many types of files:

- **Document Files:** pdf, word, xlsx, ppt, txt, md, etc
- **Media Files:** png, mp4, mp3, gif, jpg, etc
- **Program Files:** html, py, js, java, etc

They are typically given the name of the file with the format at the end

- `name-of-file.format`
- `cat.png` or `Main.java`

A file is a collection of data stored as a unit on a computer. It can contain text, images, programs, or any other type of information. **All programs and data** are written to or read from a file. These instructions, or code, are interpreted or compiled by a computer to perform specific tasks.

#### Folders (Also called directories)

Folders or directories are an organizational tool used to arrange files so you can store them neatly.

- Can hold many different types of files
- Can be named to quickly identify contents
- Can be put within each other

Some folders are specialized to contain code. These are called a repository, a special type of folder. It's a central location where code, documentation, and other project files are stored and managed. Repositories are often used with version control systems like Git to track changes, collaborate with others, and maintain a history of the project's development.

### Git & Repositories

Git is a powerful version control system that helps developers manage and track changes to their code over time. Think of it as a time machine for your projects.

A repository is a central location where all the files and folders of a project are stored. It's like a digital warehouse for your code. Git allows you to create multiple versions of your project, compare changes, and collaborate with other developers.

By using Git and repositories, developers can:

- **Track changes:** Record every modification made to the code.
- **Collaborate efficiently:** Work on projects with multiple team members simultaneously.
- **Revert to previous versions:** Roll back to earlier states of the project if needed.
- **Branch and merge**: Experiment with new features without affecting the main codebase.

Essentially, Git and repositories provide a structured and efficient way to manage software development projects.

> **NOTE:**
>
> - Git is the underlying technology, a version control system that tracks changes to files over time.
> - GitHub is a cloud-based platform that provides a user-friendly interface and additional features for working with Git repositories.
>
> They are not the same thing, GitHub uses Git as a way to manage and track code in the cloud. In essence, Git is the engine that powers version control, while GitHub is the platform that makes it accessible and collaborative.

## Let's Get Started

Now that we understand the basics of what Git is, let's start coding.

Before we do, we need to create a GitHub Account and link a GitHub Repository to the IDE. Restricted access is for those that are working on Chromebooks or computers that don't have access to GitHub. Regular access is for those that do have access to GitHub on their computer and want to sign into GitHub automatically.

- [Restricted Access: Link GitHub to VSCode](https://docs.google.com/presentation/d/1sSJEyJDiNvWRryz5Uf32xsFG6aphf1amna9CFw5BIJU/edit?usp=sharing)
- [Regular Access: Login to GitHub Account](https://code.visualstudio.com/docs/sourcecontrol/intro-to-git)

Use this guide to quickly understand and use Git Tools within VSCode:

- [Working with GitHub in VS Code](https://code.visualstudio.com/docs/sourcecontrol/github)

You will be using this GitHub repository for the rest of today's course:

- Copy this `https://github.com/Chargers-4189/git-lesson.git` or [View the repository online](https://github.com/Chargers-4189/git-lesson).

## Git Commands

1. Clone:

   - Purpose: Creates a local copy of a remote repository on your machine.
   - Command: `git clone <remote_repository_url>`
   - Example: `git clone https://github.com/user/repo.git` This will create a new directory named "repo" on your machine, containing all the files and history from the remote repository.

2. Fetch:

   - Purpose: Downloads the latest commits from a remote repository without merging them into your local branches.
   - Command: `git fetch <remote_name>`
   - Example: `git fetch origin` This will update your local repository's knowledge of the remote repository's state.

3. Pull:

   - Purpose: Fetches changes from a remote repository and merges them into your current branch.
   - Command: `git pull <remote_name> <branch_name>`
   - Example: `git pull origin main` This will fetch the latest commits from the "main" branch of the "origin" remote and merge them into your current branch.

4. Commit:

   - Purpose: Saves changes to your local repository.
   - Command: `git commit -m "commit message"`
   - Example: `git commit -m "Fixed bug in feature X"` This will create a snapshot of your current working directory and save it to your local repository.

5. Push:

   - Purpose: Uploads local commits to a remote repository.
   - Command: `git push <remote_name> <branch_name>`
   - Example: `git push origin main` This will push your local commits to the "main" branch of the "origin" remote repository.

6. Branch:

   - Purpose: Creates a new branch.
   - Command: `git branch <branch_name>`
   - Example: `git branch feature_x` This will create a new branch named "feature_x" that branches off from your current branch.

7. Checkout:

   - Purpose: Switches between branches.
   - Command: `git checkout <branch_name>`
   - Example: `git checkout main` This will switch your working directory to the "main" branch.

8. Merge:

   - Purpose: Combines changes from one branch into another.
   - Command: `git merge <branch_name>`
   - Example: `git merge feature_x` This will merge the changes from the "feature_x" branch into your current branch.

9. Status:

   - Purpose: Shows the current state of your working directory.
   - Command: git status This will display information about untracked files, changes to tracked files, and the current branch.

You can see how these commands work together in this Git Diagram
![git-workflow]

## Git Workflow

This is borrowed from [GitHub Docs](https://docs.github.com/en/get-started/using-github/github-flow) and simplified into a digestible format to understand.

There are three core concepts:

- Branches: These are separate lines of development within your project. You can think of them as parallel timelines.
- Main Branch: This is the primary branch, representing the stable, production-ready code.
- Merge: This operation combines changes from one branch into another.

Here is the workflow that we'll follow for most of our projects.

1. **Start with a Main Branch:**
   - Begin with a stable main branch.
2. **Create a Feature Branch:**
   - When starting a new feature or bug fix, create a new branch from the main branch.
3. **Develop and Test:**
   - Work on your feature or fix within this branch, making commits as you go.
4. **Create a Pull Request:**
   - Once the feature is complete, create a pull request to merge the changes back into the main branch.
5. **Review and Merge:**
   - Other developers will review your code and provide feedback. If the changes are approved, they will be merged into the main branch.
6. **Deploy:**
   - Once merged into the main branch, the changes can be deployed to production.

Here is an example illustration of a Git Workflow:

![Illustration of Git Workflow](https://nvie.com/img/git-model@2x.png)

> Note: Sometimes, urgent bugs need to be fixed immediately (emergency repairs). On tag 0.2 of the illustration, a hotfix branch can be created directly from the main branch to implement the fix. This hotfix is then merged into both the development and main branches to ensure both are updated.

> Commit often: This helps maintain a clear and concise project history.
>
> Branch strategically: Use branches to isolate work, experiment, and collaborate effectively.

Benefits of this Workflow:

- Clear Separation of Concerns: Development happens on a separate branch, so it doesn't interfere with the stable main branch.
- Flexibility and Collaboration: Multiple developers can work on features simultaneously through multiple feature branches.

Vincent Driessen provides a detailed overview on a successful Git branching model if you would like to know about this process more. It is outdated; however, it still provides valuable insights in versioning workflows.
[View the article here](https://nvie.com/posts/a-successful-git-branching-model/).

Next you'll learn conventions to ensure clear and descriptive names for commits, pull requests, and branches. This helps everyone in understanding your purpose and avoids clutter.

## Git Conventions

**Guidelines:**

> **Atomic Commits:** Commit small and focused changes. Never commit multiple changes from multiple files.
>
> **Code Review:** Actively participate in reviewing pull requests and code. Provide constructive Feedback.
>
> **Stay Updated:** Actively fetch and pull any changes from the repository.
>
> **Consistent Style Guide:** Use an extension or follow coding style guides for consistent formats and syntax.

### Git Commit Messages

- **Clear and Concise:** Use clear and concise messages that accurately describe the changes made.
- **Present Tense:** Write messages in the present tense.
- **Imperative Mood:** Use the imperative mood (e.g., "Fix bug in login form").
- **Description:** Use the description part of the message to provide more detailed insights.

Example:

```
Fix bug in login form

The login form was not validating email addresses correctly. This commit fixes the validation logic to ensure that only valid email addresses are accepted.
```

### Git Branch Naming

- **Descriptive Names:** Use clear and descriptive names that reflect the purpose of the branch.
- **Lowercase and Hyphen-Separated:** Use lowercase letters and hyphens to separate words.

Example:

- **Feature Branches:** Use a prefix like feature/ (e.g., feature/login-form).
- **Bugfix Branches:** Use a prefix like bugfix/ (e.g., bugfix/broken-link).
- **Hotfix Branches:** Use a prefix like hotfix/ (e.g., hotfix/urgent-security-fix).

#### Version Convention

When you release your software as a version, you will typically follow the Semantic Versioning 2.0.0 (SemVer 2.0.0) format, where versions are typically represented as X.Y.Z.

- **Major Version (X)**
  - Incremented when there are breaking changes that introduce incompatible APIs or significant changes in behavior.
  - This means existing code using the old version may require modifications to work with the new version.
- **Minor Version (Y)**
  - Incremented when new features are added in a backward-compatible way.
  - Existing code should continue to work without modifications.
  - These typically include feature updates.
- **Patch Version (Z)**
  - Incremented when bug fixes are made that do not introduce new features or break existing functionality.
  - These include security fixes or bug fixes.

Example:

- `v1.0.0`: Initial release of the software.
- `v1.1.0`: New feature added without breaking existing functionality.
- `v1.1.1`: Bug fix released without introducing new features or breaking existing functionality.
- `v2.0.0`: Major overhaul with significant changes that may break existing code.

### Pull Requests

#### Titles

- **Clear and Concise:** Use clear and concise titles that accurately describe the changes.
- **Present Tense:** Write titles in the present tense.
- **Imperative Mood:** Use the imperative mood (e.g., "Fix bug in login form").

Example:

- Recommended: `Fix bug in login form`
- Not recommended: `Fixed login form bug`

#### Descriptions

- **Clear and Concise:** Provide a clear and concise description of the changes.
- **Context:** Explain the context of the changes and why they were made.
- **Testing:** Describe the tests that were performed to verify the changes.

## Git Branching

Learn Git Branching is a great website to understand how branching and merges work in GitHub. I would recommend going through the a couple of the lessons to get the hang of branching.

- [Learn Git Branching](https://learngitbranching.js.org/)

[git-workflow]:
