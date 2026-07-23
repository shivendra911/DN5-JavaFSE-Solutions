import React from 'react';

function CourseDetails({ show }) {
  return (
    <div>
      {show ? (
        <div>
          <h3>Course Details</h3>
          <p>DN 5.0 - Java FSE React</p>
        </div>
      ) : null}
    </div>
  );
}

export default CourseDetails;
