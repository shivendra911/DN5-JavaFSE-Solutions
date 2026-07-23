# Git Exercise 4: Merge Conflict Resolution

## Objective
Deliberately create a conflict by editing the same file differently on two
branches, then resolve it.

---

### Step 1: Confirm master is clean
```bash
git status
```
Should say: `nothing to commit, working tree clean`

### Step 2: Create a new branch and add a file
```bash
git checkout -b GitWork
echo "<config><env>development</env></config>" > hello.xml
git add hello.xml
git commit -m "Add hello.xml with development config on GitWork"
```

### Step 3: Update the content and observe status
```bash
echo "<config><env>staging</env></config>" > hello.xml
git status
```
Shows `hello.xml` as modified.

### Step 4: Commit that update on the branch
```bash
git add hello.xml
git commit -m "Update hello.xml to staging on GitWork"
```

### Step 5: Switch to master
```bash
git checkout main
```

### Step 6: Add the SAME file with DIFFERENT content on master
```bash
echo "<config><env>production</env></config>" > hello.xml
git add hello.xml
git commit -m "Add hello.xml with production config on main"
```

At this point, `main` and `GitWork` both have `hello.xml`, but with
completely different content — a genuine conflict is now set up.

### Step 7: View the divergent history
```bash
git log --oneline --graph --decorate --all
```
You'll see two separate lines of commits touching the same file.

### Step 8: Check differences before merging
```bash
git diff main GitWork -- hello.xml
```

### Step 9: Attempt the merge
```bash
git merge GitWork
```

Expected output:
```
Auto-merging hello.xml
CONFLICT (content): Merge conflict in hello.xml
Automatic merge failed; fix conflicts and then commit the result.
```

### Step 10: Open the conflicted file
```bash
code hello.xml
```
You'll see Git's conflict markers:
```
<<<<<<< HEAD
<config><env>production</env></config>
=======
<config><env>staging</env></config>
>>>>>>> GitWork
```

### Step 11: Resolve manually
Decide what the final content should be — for example, keep staging as the
real value — and remove the `<<<<<<<`, `=======`, `>>>>>>>` markers entirely,
leaving only:
```
<config><env>staging</env></config>
```

### Step 12: Mark the conflict as resolved and commit
```bash
git add hello.xml
git commit -m "Resolve merge conflict in hello.xml - use staging config"
```

### Step 13: Clean up — add backup files to .gitignore if your editor created any
```bash
echo "*.orig" >> .gitignore
git add .gitignore
git commit -m "Ignore .orig backup files created during conflict resolution"
```

### Step 14: List and delete the merged branch
```bash
git branch -a
git branch -d GitWork
```

### Step 15: View the final clean history
```bash
git log --oneline --graph --decorate
```
This now shows the conflict resolution as a proper merge commit (not a
fast-forward), because the two branches had genuinely diverged.
