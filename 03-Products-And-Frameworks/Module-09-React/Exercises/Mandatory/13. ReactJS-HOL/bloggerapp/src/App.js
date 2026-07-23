import React, { useState } from 'react';
import BookDetails from './Components/BookDetails';
import BlogDetails from './Components/BlogDetails';
import CourseDetails from './Components/CourseDetails';

function App() {
  const [showAll, setShowAll] = useState(true);

  let statusMessage;
  if (showAll) {
    statusMessage = <p>Showing all sections</p>;
  } else {
    statusMessage = <p>Sections are hidden</p>;
  }

  return (
    <div>
      <h1>Blogger App</h1>
      <button onClick={() => setShowAll(!showAll)}>Toggle Sections</button>

      {statusMessage}

      <BookDetails show={showAll} />
      <BlogDetails show={showAll} />
      <CourseDetails show={showAll} />
    </div>
  );
}

export default App;
