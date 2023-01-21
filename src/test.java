import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class test {

    @org.junit.Test
    public void testCreateUserCommand() {

        String input = "create\nJohn Doe\nadmin\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        DesignPatterns.main(new String[]{});

        String output = out.toString();
        assertTrue(output.contains("Enter command (create, exit): User created: John Doe (Admin)"));
        assertTrue(output.contains("Enter command (create, exit): Exiting program..."));
    }
}
