import React from 'react';

function ListofPlayers() {

  const players = [
    { name: 'Rohit', score: 85 },
    { name: 'Virat', score: 92 },
    { name: 'Shubman', score: 65 },
    { name: 'Suryakumar', score: 78 },
    { name: 'Rishabh', score: 55 },
    { name: 'Hardik', score: 60 },
    { name: 'Ravindra', score: 45 },
    { name: 'Axar', score: 50 },
    { name: 'Kuldeep', score: 30 },
    { name: 'Bumrah', score: 20 },
    { name: 'Siraj', score: 15 }
  ];

  const lowScorePlayers = players.filter((p) => p.score < 70);

  return (
    <div>
      <h2>All Players</h2>
      <ul>
        {players.map((player, index) => (
          <li key={index}>{player.name} - {player.score}</li>
        ))}
      </ul>

      <h2>Players with score below 70</h2>
      <ul>
        {lowScorePlayers.map((player, index) => (
          <li key={index}>{player.name} - {player.score}</li>
        ))}
      </ul>
    </div>
  );
}

export default ListofPlayers;
