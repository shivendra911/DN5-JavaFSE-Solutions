import React from 'react';

function OfficeList() {

  const office = {
    name: 'Cognizant Tech Park',
    rent: 75000,
    address: 'OMR, Chennai'
  };

  const officeList = [
    { name: 'WeWork Koramangala', rent: 55000, address: 'Bangalore' },
    { name: 'Cognizant Tech Park', rent: 75000, address: 'Chennai' },
    { name: 'DLF Cyber City', rent: 45000, address: 'Gurgaon' }
  ];

  return (
    <div>
      <h1>Office Space Rental Listings</h1>

      <img
        src="https://via.placeholder.com/300x150"
        alt="Office space"
        width="300"
      />

      <h2>Featured Office</h2>
      <p>Name: {office.name}</p>
      <p>Rent: {office.rent}</p>
      <p>Address: {office.address}</p>

      <h2>All Available Offices</h2>
      {officeList.map((item, index) => (
        <div key={index}>
          <p>Name: {item.name}</p>
          <p style={{ color: item.rent < 60000 ? 'red' : 'green' }}>
            Rent: {item.rent}
          </p>
          <p>Address: {item.address}</p>
          <hr />
        </div>
      ))}
    </div>
  );
}

export default OfficeList;
