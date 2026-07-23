import React from 'react';

function IndianPlayers() {

  const team = { oddTeam: 'Team A', evenTeam: 'Team B' };

  const { oddTeam, evenTeam } = team;

  const T20players = ['Rohit', 'Virat', 'Suryakumar'];
  const RanjiTrophyPlayers = ['Sarfaraz', 'Yashasvi', 'Tilak'];

  const allPlayers = [...T20players, ...RanjiTrophyPlayers];

  return (
    <div>
      <h2>Team Split (Destructuring)</h2>
      <p>Odd Team Players: {oddTeam}</p>
      <p>Even Team Players: {evenTeam}</p>

      <h2>Merged Players (Spread)</h2>
      <ul>
        {allPlayers.map((player, index) => (
          <li key={index}>{player}</li>
        ))}
      </ul>
    </div>
  );
}

export default IndianPlayers;
