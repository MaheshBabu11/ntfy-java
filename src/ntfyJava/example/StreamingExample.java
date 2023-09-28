package ntfyJava.example;

import ntfyJava.core.service.StreamingService;

public class StreamingExample {
    public static void main(String[] args) {
        String streamingUrl = "https://ntfy.sh/test_ntfy/json";

        StreamingService streamingConnection = new StreamingService(streamingUrl);

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
