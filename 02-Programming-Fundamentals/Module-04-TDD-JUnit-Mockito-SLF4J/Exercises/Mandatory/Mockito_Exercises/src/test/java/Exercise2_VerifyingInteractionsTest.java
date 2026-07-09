// Exercise 2: Verifying Interactions
// Making sure that methods on the mock are actually called

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

public class Exercise2_VerifyingInteractionsTest {

    @Test
    public void testVerifyInteraction() {
        // create mock
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // create service and call fetchData
        MyService service = new MyService(mockApi);
        service.fetchData();

        // verify that getData() was actually called on the mock
        verify(mockApi).getData();
        System.out.println("Verified: getData() was called on the mock.");
    }

    @Test
    public void testVerifyInteractionCalledTwice() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        MyService service = new MyService(mockApi);

        // call fetchData twice
        service.fetchData();
        service.fetchData();

        // verify getData was called exactly 2 times
        verify(mockApi, times(2)).getData();
        System.out.println("Verified: getData() was called 2 times.");
    }
}
