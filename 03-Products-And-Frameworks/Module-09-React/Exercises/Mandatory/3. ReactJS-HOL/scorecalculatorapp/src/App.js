import React from 'react';
import CalculateScore from './Components/CalculateScore';

function App() {
  return (
    <div>
      <CalculateScore name="Rahul Sharma" school="DAV Public School" total={450} goal={500} />
    </div>
  );
}

export default App;
