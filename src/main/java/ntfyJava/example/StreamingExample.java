package ntfyJava.example;

import ntfyJava.core.exception.NtfyStreamingException;
import ntfyJava.core.model.StreamRequest;
import ntfyJava.core.stream.StreamingService;

public class StreamingExample {
    public static void main(String[] args) throws NtfyStreamingException {

        StreamRequest request = new StreamRequest();
        request.setHost("ntfy.sh");
        request.setTopic("test_ntfy2");

        StreamingService streamingConnection = new StreamingService(request);
        streamingConnection.setDataListener(data -> {
            // Process the incoming data here
            System.out.println("Received data: " + data);
        });

        // Start the streaming connection
        streamingConnection.start();

        // Keep the connection open and receive data indefinitely
        // To stop the connection, call streamingConnection.stop()
    }
}
