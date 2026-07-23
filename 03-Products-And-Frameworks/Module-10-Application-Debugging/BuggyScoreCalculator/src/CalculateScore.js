import React, { useState } from 'react';

// This is a DELIBERATELY BUGGY version of Exercise 3's CalculateScore
// component, built for Module 10 - Application Debugging practice.
// It has 3 real bugs. Use Chrome DevTools + VS Code debugger to find them
// before reading the ANSWERS section at the bottom of this file.

function CalculateScore(props) {
  const [marks, setMarks] = useState([]);
  const [subject, setSubject] = useState('');

  // BUG 1 lives somewhere in this function - it will show as NaN in the UI
  function calculateAverage() {
    let total = 0;
    for (let i = 0; i <= marks.length; i++) {   // <-- suspicious loop bound
      total += marks[i];
    }
    return total / marks.length;
  }

  function handleAddMark(event) {
    const value = event.target.value;
    setMarks([...marks, value]);   // <-- suspicious: what type is 'value'?
  }

  // BUG 2 lives in this click handler - button appears to do nothing
  function handleSubjectChange(event) {
    subject = event.target.value;   // <-- suspicious assignment
  }

  return (
    <div>
      <h2>Score Calculator (Buggy Version)</h2>

      <input
        type="text"
        placeholder="Subject name"
        onChange={handleSubjectChange}
      />
      <p>Current subject: {subject}</p>

      <input
        type="number"
        placeholder="Enter a mark"
        onKeyDown={(e) => {
          if (e.key === 'Enter') handleAddMark(e);
        }}
      />

      <ul>
        {marks.map((m) => (
          <li>{m}</li>   // BUG 3 is on this line - React console warning
        ))}
      </ul>

      <p>Average: {calculateAverage()}</p>
    </div>
  );
}

export default CalculateScore;

/*
=====================================================================
DEBUGGING WALKTHROUGH - work through this using the tools, not by
reading ahead. Use these steps as your guide, then check ANSWERS.
=====================================================================

## Using Chrome DevTools

1. Run the app (npm start), open Chrome DevTools (F12) -> Console tab.
2. Type a subject name -> notice "Current subject:" text never updates.
   That's your first clue: state isn't being triggered.
3. Add a couple of marks and press Enter after each -> open the
   Elements/Console tab and look for a red React warning about
   "each child in a list should have a unique key prop" -> that's Bug 3.
4. Look at the Average line -> it shows "NaN". Open the Sources tab,
   find CalculateScore.js, and click the line number next to
   "total += marks[i]" to set a breakpoint.
5. Reload, add marks, and watch execution pause at your breakpoint.
   Use the "Step Over" button (curved arrow icon) to advance one line
   at a time. Hover over "marks[i]" and "i" to inspect their live values
   in the tooltip that appears.
6. On the LAST loop iteration, you'll see marks[i] is `undefined` -
   that's the loop reading one index past the end of the array. That's Bug 1.

## Using VS Code Debugger

1. Open the project folder in VS Code.
2. Go to Run and Debug (Ctrl+Shift+D) -> create a launch.json for
   "Chrome" if prompted, pointing url to http://localhost:3000.
3. Click the gutter (left of the line number) next to
   "subject = event.target.value;" to set a breakpoint directly in VS Code.
4. Start debugging (F5), type into the subject input in the browser.
5. Execution pauses at your breakpoint in VS Code. Look at the
   Variables panel on the left - you'll see 'subject' listed as a
   plain local variable, NOT as state. That's your clue for Bug 2:
   direct assignment to `subject` doesn't call setSubject(), so React
   never knows to re-render.

=====================================================================
ANSWERS (only read after actually trying the steps above)
=====================================================================

BUG 1: `for (let i = 0; i <= marks.length; i++)` should be `i < marks.length`.
       Using <= reads one index past the array's end, which is undefined,
       and undefined + anything = NaN.

BUG 2: `subject = event.target.value;` directly reassigns a variable
       instead of calling `setSubject(event.target.value)`. React only
       re-renders when you call the state setter function - direct
       assignment is invisible to React entirely.

BUG 3: `<li>{m}</li>` is missing a `key` prop, e.g. `<li key={index}>{m}</li>`.
       React needs a stable key to efficiently track list items across
       re-renders; without it, you get a console warning and, in more
       complex lists, actual rendering bugs when items are reordered.
=====================================================================
*/
