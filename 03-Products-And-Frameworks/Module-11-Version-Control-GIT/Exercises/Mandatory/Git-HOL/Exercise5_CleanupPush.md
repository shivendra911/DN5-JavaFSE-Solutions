# Git Exercise 5: Cleanup and Push to Remote

## Objective
Confirm your working directory is clean, sync with the remote, and push any
pending local commits from Exercise 4.

---

### Step 1: Confirm master is in a clean state
```bash
git status
```
Should say: `nothing to commit, working tree clean`

### Step 2: List all available branches
```bash
git branch -a
```
By now `GitWork` and `GitNewBranch` should already be deleted (from
Exercises 3 and 4) — only `main` should remain locally.

### Step 3: Pull the latest changes from remote
```bash
git pull origin main
```
This protects against overwriting anything a teammate (or your other
machine) may have pushed in the meantime.

### Step 4: Push all pending local commits
```bash
git push origin main
```
This uploads the conflict-resolution commit from Exercise 4 and the
`.gitignore` update, if they weren't already pushed.

### Step 5: Verify the remote reflects everything
```bash
git log --oneline --graph --decorate
```
Then open the repository on GitHub/GitLab in a browser and confirm:
- `hello.xml` shows the resolved (staging) content
- `.gitignore` includes the `*.orig` rule
- The commit history matches what you see locally

If all three match, the sync is complete and this hands-on is done.
