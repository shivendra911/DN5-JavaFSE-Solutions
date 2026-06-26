# Exercise-01-Singleton-Pattern

This project demonstrates the Singleton Design Pattern in Java.

## Overview
The Singleton pattern is a creational design pattern that lets you ensure that a class has only one instance, while providing a global access point to this instance.

This pattern involves a single class which is responsible to create an object while making sure that only a single object gets created. This class provides a way to access its only object which can be accessed directly without need to instantiate the object of the class.

## Files
- `Singleton.java`: The Singleton class with a private constructor and a `getInstance()` method.
- `SingletonPatternDemo.java`: The demo class that uses the Singleton class.
- `Output.png`: Screenshot of the output.

## How to run
1. Compile the Java files:
   ```bash
   javac SingletonPatternDemo.java Singleton.java
   ```
2. Run the demo:
   ```bash
   java SingletonPatternDemo
   ```
