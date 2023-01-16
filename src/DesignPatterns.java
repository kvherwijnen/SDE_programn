import java.util.Scanner;

public class DesignPatterns {
    public static void main(String[] args) {
        InputScanner scanner = InputScanner.getInstance();
        boolean exit = false;

        while (!exit) {
            System.out.print("Enter command (create, exit): ");
            String command = scanner.nextLine();

            if (command.equalsIgnoreCase("create")) {
                System.out.print("Enter name: ");
                String name = scanner.nextLine();

                System.out.print("Enter role (admin, regular): ");
                String role = scanner.nextLine();

                Command createUserCommand = new CreateUserCommand(name, role);
                createUserCommand.execute();
            } else if (command.equalsIgnoreCase("exit")) {
                Command exitCommand = new ExitCommand();
                exitCommand.execute();
                exit = true;
            } else {
                System.out.println("Invalid command");
            }
        }
    }

    // Using the Singleton pattern for the console input scanner
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

    // Using the Factory pattern to create user objects
    interface User {
        String getName();
        String getRole();
    }

    static class Admin implements User {
        private final String name;

        public Admin(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public String getRole() {
            return "Admin";
        }
    }

    static class RegularUser implements User {
        private final String name;

        public RegularUser(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public String getRole() {
            return "Regular User";
        }
    }

    static class UserFactory {
        public static User createUser(String name, String role) {
            if (role.equalsIgnoreCase("admin")) {
                return new Admin(name);
            } else {
                return new RegularUser(name);
            }
        }
    }

    // Using the Command pattern to handle user input
    interface Command {
        void execute();
    }

    static class ExitCommand implements Command {
        public void execute() {
            System.out.println("Exiting program...");
            System.exit(0);
        }
    }

    static class CreateUserCommand implements Command {
        private final String name;
        private final String role;

        public CreateUserCommand(String name, String role) {
            this.name = name;
            this.role = role;
        }

        public void execute() {
            User user = UserFactory.createUser(name, role);
            System.out.println("User created: " + user.getName() + " (" + user.getRole() + ")");
        }
    }
}
