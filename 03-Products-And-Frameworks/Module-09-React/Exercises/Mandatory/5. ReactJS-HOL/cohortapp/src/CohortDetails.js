import React from 'react';
import styles from './CohortDetails.module.css';

function CohortDetails(props) {
  const headingStyle = {
    color: props.status === 'ongoing' ? 'green' : 'blue'
  };

  return (
    <div className={styles.box}>
      <h3 style={headingStyle}>{props.cohortName}</h3>
      <dl>
        <dt>Status</dt>
        <dd>{props.status}</dd>
        <dt>Start Date</dt>
        <dd>{props.startDate}</dd>
      </dl>
    </div>
  );
}

export default CohortDetails;
