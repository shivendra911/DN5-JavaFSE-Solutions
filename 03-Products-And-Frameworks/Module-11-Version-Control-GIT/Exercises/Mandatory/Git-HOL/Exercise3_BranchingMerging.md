# Git Exercise 3: Branching and Merging

## Objective
Create a new branch, make changes on it, then merge it cleanly back into
main/master.

---

## Branching

### Step 1: Create a new branch
```bash
git branch GitNewBranch
```

### Step 2: List all branches (local and remote)
```bash
git branch -a
```
The branch with `*` next to it is the one you're currently on — at this
point that's still `main`, since creating a branch doesn't switch to it.

### Step 3: Switch to the new branch
```bash
git checkout GitNewBranch
```
(Modern equivalent: `git switch GitNewBranch`)

### Step 4: Add a file with content on this branch
```bash
echo "This feature was built on GitNewBranch" > feature.txt
git add feature.txt
```

### Step 5: Commit the change
```bash
git commit -m "Add feature.txt on GitNewBranch"
```

### Step 6: Check status
```bash
git status
```
Should show: `On branch GitNewBranch` and `nothing to commit, working tree clean`

---

## Merging

### Step 7: Switch back to main
```bash
git checkout main
```

### Step 8: See differences between main and the branch (command line)
```bash
git diff main GitNewBranch
```

### Step 9 (optional): Visual diff using a merge tool
```bash
git difftool main GitNewBranch
```
(Requires a diff tool like P4Merge or VS Code configured as `git difftool`)

### Step 10: Merge the branch into main
```bash
git merge GitNewBranch
```
Since main hasn't changed since the branch was created, this will be a
"fast-forward" merge — no conflicts.

### Step 11: View the commit history graph
```bash
git log --oneline --graph --decorate
```
This shows the merge visually as a straight line (fast-forward) rather than
a diverging/converging graph.

### Step 12: Delete the now-merged branch
```bash
git branch -d GitNewBranch
```
Git only allows this because the branch's changes are already merged; if
they weren't, it would refuse and ask you to use `-D` to force it.

### Step 13: Confirm it's gone
```bash
git branch -a
git status
```
