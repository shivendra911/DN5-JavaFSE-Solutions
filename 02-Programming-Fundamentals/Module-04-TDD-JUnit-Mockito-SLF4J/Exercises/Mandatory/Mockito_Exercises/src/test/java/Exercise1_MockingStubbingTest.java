// Exercise 1: Mocking and Stubbing
// Testing MyService without calling the real ExternalApi

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class Exercise1_MockingStubbingTest {

    @Test
    public void testExternalApi() {
        // create a mock object for ExternalApi
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // stub the method - tell mock what to return when called
        when(mockApi.getData()).thenReturn("Mock Data");

        // use the mock in our service
        MyService service = new MyService(mockApi);
        String result = service.fetchData();

        // verify the result is what we stubbed
        assertEquals("Mock Data", result);
        System.out.println("Result from mock: " + result);
    }
}
