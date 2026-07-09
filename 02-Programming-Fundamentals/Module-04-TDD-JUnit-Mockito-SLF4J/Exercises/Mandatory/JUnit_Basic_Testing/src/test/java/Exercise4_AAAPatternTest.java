import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class Exercise4_AAAPatternTest {

    // This object is shared across all tests in this class
    private Calculator calculator;

    // runs before each test - setup
    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
        System.out.println("Setup done - Calculator created");
    }

    // runs after each test - teardown
    @AfterEach
    public void tearDown() {
        calculator = null;
        System.out.println("Teardown done - Calculator cleared");
    }

    // runs once before all tests in this class
    @BeforeAll
    public static void initAll() {
        System.out.println("Starting all tests in Exercise4_AAAPatternTest");
    }

    // runs once after all tests in this class
    @AfterAll
    public static void cleanupAll() {
        System.out.println("All tests in Exercise4_AAAPatternTest finished");
    }

    @Test
    public void testAdd() {
        // Arrange
        int a = 10;
        int b = 5;

        // Act
        int result = calculator.add(a, b);

        // Assert
        assertEquals(15, result);
    }

    @Test
    public void testSubtract() {
        // Arrange
        int a = 20;
        int b = 8;

        // Act
        int result = calculator.subtract(a, b);

        // Assert
        assertEquals(12, result);
    }

    @Test
    public void testMultiply() {
        // Arrange
        int a = 4;
        int b = 6;

        // Act
        int result = calculator.multiply(a, b);

        // Assert
        assertEquals(24, result);
    }

    @Test
    public void testDivide() {
        // Arrange
        int a = 10;
        int b = 2;

        // Act
        double result = calculator.divide(a, b);

        // Assert
        assertEquals(5.0, result);
    }
}
