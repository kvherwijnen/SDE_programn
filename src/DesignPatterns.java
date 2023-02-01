import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

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

    // Structural Design Pattern: Adapter Pattern
    interface User {
        String getName();
        String getRole();
    }

    class Admin implements User {
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

    class LegacyUser {
        private final String name;
        private final String role;

        public LegacyUser(String name, String role) {
            this.name = name;
            this.role = role;
        }

        public String getName() {
            return name;
        }

        public String getRole() {
            return role;
        }
    }

    class LegacyUserAdapter implements User {
        private final LegacyUser legacyUser;

        public LegacyUserAdapter(LegacyUser legacyUser) {
            this.legacyUser = legacyUser;
        }

        @Override
        public String getName() {
            return legacyUser.getName();
        }

        @Override
        public String getRole() {
            return legacyUser.getRole();
        }
    }

    // Creational Design Pattern: Abstract Factory Pattern
    interface UserAbstractFactory {
        User createUser();
        UserDecorator createUserDecorator();
    }

    class AdminUserAbstractFactory implements UserAbstractFactory {
        @Override
        public User createUser() {
            return new Admin(name);
        }

        @Override
        public UserDecorator createUserDecorator() {
            return new UserDecoratorImpl();
        }
    }

    class RegularUserAbstractFactory implements UserAbstractFactory {
        @Override
        public User createUser() {
            return new RegularUser(name);
        }

        @Override
        public UserDecorator createUserDecorator() {
            return new UserDecoratorImpl();
        }
    }

    // Behavioral Design Pattern: Observer Pattern
    interface Observer {
        void update();
    }

    interface Subject {
        void registerObserver(Observer observer);
        void removeObserver(Observer observer);
        void notifyObservers();
    }

    class UserSubject implements Subject {
        private final Set<Observer> observers = new HashSet<>();
        private User user;

        public void registerObserver(Observer observer) {
            this.observers.add(observer);
        }

        public void removeObserver(Observer observer) {
            this.observers.remove(observer);
        }

        public void notifyObservers() {
            for (Observer observer : observers) {
                observer.update();
            }
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
            notifyObservers();
        }
    }

    class UserObserver implements Observer {
        private UserSubject subject;

        public UserObserver(UserSubject subject) {
            this.subject = subject;
            subject.registerObserver(this);
        }

        @Override
        public void update() {
            System.out.println("User changed: " + subject.getUser().getName());
        }
    }

    // Using the decorater pattern to allow
    interface UserDecorator extends User {
        void setUser(User user);
        void addPermission(String permission);
        boolean hasPermission(String permission);
    }

    static class UserDecoratorImpl implements UserDecorator {
        private User user;
        private final Set<String> permissions = new HashSet<>();

        public void setUser(User user) {
            this.user = user;
        }

        public void addPermission(String permission) {
            this.permissions.add(permission);
        }

        public boolean hasPermission(String permission) {
            return this.permissions.contains(permission);
        }

        @Override
        public String getName() {
            return user.getName();
        }

        @Override
        public String getRole() {
            return user.getRole();
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
            UserDecoratorImpl userDecorator = new UserDecoratorImpl();
            userDecorator.setUser(user);
            userDecorator.addPermission("create");
            userDecorator.addPermission("read");
            System.out.println("User created: " + user.getName() + " (" + user.getRole() + ") with permissions: "+ userDecorator.permissions);
        }
    }
}
