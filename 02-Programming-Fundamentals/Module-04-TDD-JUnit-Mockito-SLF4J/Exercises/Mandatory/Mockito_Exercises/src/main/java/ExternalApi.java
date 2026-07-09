// External API interface - represents a third party service
// We will mock this in our tests instead of calling the real API

public interface ExternalApi {
    String getData();
}
