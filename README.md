# SDE_programn OLD
Java console based program

This code uses several design patterns in a program that takes user input and creates a user object based on that input. 

The Singleton pattern is applied to the console input scanner to ensure that only one instance is used throughout the program. 

The Factory pattern is used to create user objects, where the `UserFactory` class has a static `createUser` method that takes in a name and role, and returns the correct type of User object (`Admin` or `RegularUser`). 

The Decorator pattern adds a new functionality to the User class without having to change the implementation of the `Admin` and `RegularUser` classes

The Command pattern is used to handle user input, where the program prompts the user for a command (either `create` or `exit`) and passes it to the corresponding command object (`CreateUserCommand` or `ExitCommand`) to execute the appropriate action. 

The main method uses a while loop to repeatedly prompt for input and execute commands until the user chooses to `exit`. 

# Resit SDE Program
The code is a Java program that implements several design patterns. The main part of the code implements a simple command line interface that accepts two commands: "create" and "exit". The "create" command creates a new user by asking the user for a name and a role ("admin" or "regular"). The program uses several design patterns to manage the creation of the user:

**Singleton pattern:** Used for the console input scanner, to ensure that there is only one instance of the input scanner.
**Adapter pattern:** Used to adapt the data of a legacy user to the current user interface.
**Abstract Factory** pattern: Used to create different types of users based on their role.
**Observer pattern:** Used to notify other parts of the program when a user is created.
**Decorator pattern:** Used to add additional behavior to the user.
**Command pattern:** Used to handle user input, where the program prompts the user for a command.

## Feedback Andries Nieuwenhuize ##
The singleton design pattern is not well implemented since it does not avoid for multiple instances.
The UserFactory is not a design pattern but an example of a simple factory (@see https://refactoring.guru/design-patterns/factory-comparison).


**Singleton old**

    static class InputScanner {
        private static final InputScanner instance = new InputScanner();
        private final Scanner scanner;

        private InputScanner() {
            scanner = new Scanner(System.in);
        }

        public static InputScanner getInstance() {
            return instance;
        }

        public String nextLine() {
            return scanner.nextLine();
        }
    }
**Singleton New**

    static class InputScanner {
        private static InputScanner instance;
        private final Scanner scanner;

        private InputScanner() {
            scanner = new Scanner(System.in);
        }

        public static InputScanner getInstance() {
            if (instance == null) {
                instance = new InputScanner();
            }
            return instance;
        }

        public String nextLine() {
            return scanner.nextLine();
        }
    }

The updated code ensures that there is only one instance of the InputScanner class by using a static instance variable and a private constructor. The instance is created only if it is null, and the only way to get an instance is through the getInstance method, which returns the singleton instance.

