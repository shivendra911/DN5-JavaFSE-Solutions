# Git Exercise 1: Git Setup, Configuration & First Commit

## Objective
Set up Git, configure a default editor, and add your first tracked file to a
repository, then push it to a remote (GitLab or GitHub).

---

## Step 1: Verify Git is installed
```bash
git --version
```
Expected output something like: `git version 2.43.0.windows.1`

## Step 2: Set your identity (required before any commit)
```bash
git config --global user.name "Shivendra"
git config --global user.email "your-email@example.com"
```

Verify it was set:
```bash
git config --global user.name
git config --global user.email
```

## Step 3 (optional): Set a default editor
If you use VS Code instead of notepad++ (recommended, since you already have it installed):
```bash
git config --global core.editor "code --wait"
```

Verify the full config:
```bash
git config --global --list
```

## Step 4: Create and initialize the GitDemo repository
```bash
mkdir GitDemo
cd GitDemo
git init
```

Check the hidden `.git` folder was created:
```bash
ls -la
```

## Step 5: Create a tracked file
```bash
echo "Welcome to my first Git repository!" > welcome.txt
cat welcome.txt
```

## Step 6: Check status (file exists but is untracked)
```bash
git status
```
At this point `welcome.txt` shows up under "Untracked files".

## Step 7: Stage the file
```bash
git add welcome.txt
git status
```
Now it shows under "Changes to be committed".

## Step 8: Commit with a message
```bash
git commit -m "Initial commit: added welcome.txt"
```

## Step 9: Create a remote repository
1. Go to GitHub (or GitLab) → create a new repository named `GitDemo`
2. Do NOT initialize it with a README (keep it empty, since you already have local commits)
3. Copy the HTTPS URL it gives you

## Step 10: Link local repo to remote and push
```bash
git remote add origin https://github.com/<your-username>/GitDemo.git
git branch -M main
git push -u origin main
```

## Step 11: Verify
```bash
git status
```
Should show: `Your branch is up to date with 'origin/main'.`

Refresh the GitHub page — `welcome.txt` should now be visible online.
