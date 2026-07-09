import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Exercise3_AssertionsTest {

    @Test
    public void testAssertions() {

        // assertEquals - checks if two values are equal
        assertEquals(5, 2 + 3);

        // assertTrue - checks if condition is true
        assertTrue(5 > 3);

        // assertFalse - checks if condition is false
        assertFalse(5 < 3);

        // assertNull - checks if object is null
        assertNull(null);

        // assertNotNull - checks if object is not null
        assertNotNull(new Object());

        System.out.println("All assertions passed!");
    }

    @Test
    public void testCalculatorAssertions() {
        Calculator calc = new Calculator();

        // testing add
        assertEquals(10, calc.add(6, 4));

        // testing subtract
        assertEquals(5, calc.subtract(10, 5));

        // testing multiply
        assertEquals(20, calc.multiply(4, 5));

        // testing divide
        assertEquals(2.5, calc.divide(5, 2));
    }

    @Test
    public void testDivideByZeroThrowsException() {
        Calculator calc = new Calculator();

        // assertThrows - checks that an exception is thrown
        assertThrows(ArithmeticException.class, () -> {
            calc.divide(10, 0);
        });
    }
}
