import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Exercise1_JUnitSetupTest {

    @Test
    public void testJUnitIsWorking() {
        // simple test to verify JUnit setup is correct
        Calculator calc = new Calculator();
        int result = calc.add(2, 3);
        assertEquals(5, result);
        System.out.println("JUnit setup is working! Result: " + result);
    }
}
