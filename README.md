# SDE_programn
Java console based program

This code uses several design patterns in a program that takes user input and creates a user object based on that input. 

The Singleton pattern is applied to the console input scanner to ensure that only one instance is used throughout the program. 

The Factory pattern is used to create user objects, where the `UserFactory` class has a static `createUser` method that takes in a name and role, and returns the correct type of User object (`Admin` or `RegularUser`). 

The Decorator pattern adds a new functionality to the User class without having to change the implementation of the `Admin` and `RegularUser` classes

The Command pattern is used to handle user input, where the program prompts the user for a command (either `create` or `exit`) and passes it to the corresponding command object (`CreateUserCommand` or `ExitCommand`) to execute the appropriate action. 

The main method uses a while loop to repeatedly prompt for input and execute commands until the user chooses to `exit`. 
