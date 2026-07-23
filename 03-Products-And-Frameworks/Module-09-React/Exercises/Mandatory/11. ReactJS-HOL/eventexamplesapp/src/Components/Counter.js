import React, { Component } from 'react';

class Counter extends Component {

  constructor(props) {
    super(props);
    this.state = { count: 0 };

    this.handleIncrement = this.handleIncrement.bind(this);
    this.handleDecrement = this.handleDecrement.bind(this);
  }

  handleIncrement() {
    this.increaseCount();
    this.sayHello();
  }

  increaseCount() {
    this.setState({ count: this.state.count + 1 });
  }

  sayHello() {
    console.log('Hello! Counter was incremented.');
  }

  handleDecrement() {
    this.setState({ count: this.state.count - 1 });
  }

  sayWelcomeMessage(message) {
    alert(message);
  }

  handlePress = () => {
    alert('I was clicked');
  };

  render() {
    return (
      <div>
        <h2>Counter: {this.state.count}</h2>
        <button onClick={this.handleIncrement}>Increment</button>
        <button onClick={this.handleDecrement}>Decrement</button>

        <br /><br />

        <button onClick={() => this.sayWelcomeMessage('welcome')}>Say Welcome</button>

        <br /><br />

        <button onPress={this.handlePress} onClick={this.handlePress}>
          Synthetic Event Button
        </button>
      </div>
    );
  }
}

export default Counter;
