# Git Exercise 2: Using .gitignore

## Objective
Create a `.log` file and a `log` folder, then configure `.gitignore` so Git
never tracks them — even after `git add .`

---

## Step 1: Create the files to be ignored
(Run this from inside your `GitDemo` folder, continuing from Exercise 1)

```bash
echo "This is a log entry" > app.log
mkdir log
echo "debug info" > log/debug.txt
```

## Step 2: Check status BEFORE adding .gitignore
```bash
git status
```
At this point, `app.log` and `log/` show up as untracked — Git doesn't know
to ignore them yet.

## Step 3: Create the .gitignore file
```bash
touch .gitignore
```

Add these two lines inside it (open in VS Code: `code .gitignore`):
```
*.log
log/
```

Save the file.

## Step 4: Stage and commit the .gitignore file itself
```bash
git add .gitignore
git commit -m "Add .gitignore to exclude log files and log folder"
```

## Step 5: Verify the ignore rule is working
```bash
git status
```

Expected: `app.log` and `log/` no longer appear anywhere in the status output
— not even under "Untracked files". Only the `.gitignore` addition shows up
as already committed.

## Step 6: Confirm with git check-ignore
```bash
git check-ignore -v app.log
git check-ignore -v log/debug.txt
```
This prints which rule in `.gitignore` is matching each file — useful proof
that the ignore rule is actually working, not just coincidentally absent.

## Step 7: Push the change
```bash
git push origin main
```
