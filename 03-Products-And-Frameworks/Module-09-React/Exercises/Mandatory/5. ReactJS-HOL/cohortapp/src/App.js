import React from 'react';
import CohortDetails from './CohortDetails';

function App() {
  return (
    <div>
      <h1>Cognizant Academy - Cohort Dashboard</h1>
      <CohortDetails cohortName="DN 5.0 Java FSE" status="ongoing" startDate="01-Jul-2026" />
      <CohortDetails cohortName="DN 4.0 Python FSE" status="completed" startDate="01-Jan-2026" />
    </div>
  );
}

export default App;
