import React, { Component } from 'react';

class CurrencyConvertor extends Component {

  constructor(props) {
    super(props);
    this.state = { rupees: '', euro: null };

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    this.setState({ rupees: event.target.value });
  }

  handleSubmit() {
    const rupeeValue = parseFloat(this.state.rupees);
    const euroValue = rupeeValue / 90;
    this.setState({ euro: euroValue.toFixed(2) });
  }

  render() {
    return (
      <div>
        <h2>Currency Convertor (INR to EUR)</h2>
        <input
          type="number"
          placeholder="Enter amount in Rupees"
          value={this.state.rupees}
          onChange={this.handleChange}
        />
        <button onClick={this.handleSubmit}>Convert</button>

        {this.state.euro !== null && (
          <p>{this.state.rupees} INR = {this.state.euro} EUR</p>
        )}
      </div>
    );
  }
}

export default CurrencyConvertor;
