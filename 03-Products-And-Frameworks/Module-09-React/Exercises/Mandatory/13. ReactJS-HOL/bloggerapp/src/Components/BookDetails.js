import React from 'react';

function BookDetails({ show }) {
  if (!show) {
    return null;
  }

  return (
    <div>
      <h3>Book Details</h3>
      <p>Clean Code by Robert C. Martin</p>
    </div>
  );
}

export default BookDetails;
