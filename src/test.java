import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class test {

    @org.junit.Test
    public void testCreateUserCommand() {
        // set up input for the test
        String input = "create\nJohn Doe\nadmin\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // set up output for the test
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // run the main method of the DesignPatterns class
        DesignPatterns.main(new String[]{});

        // check if the output is as expected
        String output = out.toString();
        assertTrue(output.contains("Enter command (create, exit): User created: John Doe (Admin)"));
        assertTrue(output.contains("Enter command (create, exit): Exiting program..."));
    }
}
