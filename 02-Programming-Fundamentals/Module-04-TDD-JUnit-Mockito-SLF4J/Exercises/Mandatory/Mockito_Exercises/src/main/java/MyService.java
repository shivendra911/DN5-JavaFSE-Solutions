// Service class that uses ExternalApi
// We inject the dependency through constructor so it can be mocked in tests

public class MyService {

    private ExternalApi externalApi;

    public MyService(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }

    public String fetchData() {
        // calls the external API to get data
        return externalApi.getData();
    }
}
