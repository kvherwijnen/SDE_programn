# SDE_programn OLD
Java console based program

This code uses several design patterns in a program that takes user input and creates a user object based on that input. 

The Singleton pattern is applied to the console input scanner to ensure that only one instance is used throughout the program. 

The Factory pattern is used to create user objects, where the `UserFactory` class has a static `createUser` method that takes in a name and role, and returns the correct type of User object (`Admin` or `RegularUser`). 

The Decorator pattern adds a new functionality to the User class without having to change the implementation of the `Admin` and `RegularUser` classes

The Command pattern is used to handle user input, where the program prompts the user for a command (either `create` or `exit`) and passes it to the corresponding command object (`CreateUserCommand` or `ExitCommand`) to execute the appropriate action. 

The main method uses a while loop to repeatedly prompt for input and execute commands until the user chooses to `exit`. 

# Resit SDE Program

I implemented the following design patterns:

**Singleton pattern:** The InputScanner class uses the singleton pattern to ensure that only one instance of the Scanner class is created. The getInstance() method returns the single instance of the InputScanner class, creating one if it doesn't already exist. This is useful to ensure that multiple instances of the Scanner class do not interfere with each other.

**Decorator pattern:**
The UserDecoratorImpl class implements the UserDecorator interface and adds additional functionality to the User objects. It adds the ability to manage user permissions. The setUser() method sets the User object that the UserDecoratorImpl class will decorate, and the addPermission() and hasPermission() methods allow for adding and checking user permissions.

**Command pattern:**
The Command interface and its implementations (ExitCommand and CreateUserCommand) handle user input. The execute() method of each implementation defines the behavior of the Command object when it is executed. This is useful for encapsulating the behavior of an action, allowing it to be easily reused and passed around as an object.

## Feedback Andries Nieuwenhuize ##
The singleton design pattern is not well implemented since it does not avoid for multiple instances.

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

## Feedback Andries Nieuwenhuize ##
The UserFactory is not a design pattern but an example of a simple factory (@see https://refactoring.guru/design-patterns/factory-comparison).
