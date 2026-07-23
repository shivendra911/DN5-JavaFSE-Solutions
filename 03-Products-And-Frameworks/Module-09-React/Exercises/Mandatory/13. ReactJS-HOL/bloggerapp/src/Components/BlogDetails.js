import React from 'react';

function BlogDetails({ show }) {
  const blogs = ['React Basics', 'JSX Deep Dive', 'State vs Props'];

  return (
    <div>
      {show && (
        <div>
          <h3>Blog Details</h3>
          <ul>
            {blogs.map((blog, index) => (
              <li key={index}>{blog}</li>
            ))}
          </ul>
        </div>
      )}
    </div>
  );
}

export default BlogDetails;
