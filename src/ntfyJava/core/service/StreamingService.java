package ntfyJava.core.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class StreamingService {
    private final String streamingUrl;
    private boolean running;
    private StreamingDataListener dataListener;

    public StreamingService(String streamingUrl) {
        this.streamingUrl = streamingUrl;
    }

    public void start() {
        running = true;
        Thread streamThread = new Thread(() -> {
            try {
                URL url = new URL(streamingUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod(NtfyConstants.GET);

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                while (running) {
                    String line = reader.readLine();
                    if (line != null) {
                        if (dataListener != null) {
                            dataListener.onDataReceived(line);
                        }
                    }
                }
                reader.close();
                connection.disconnect();
            } catch (IOException e) {
                // Handle exceptions, log errors, and clean up resources
                e.printStackTrace();
            }
        });

        streamThread.start();
    }

    public void stop() {
        running = false;
    }

    public void setDataListener(StreamingDataListener listener) {
        this.dataListener = listener;
    }

    public interface StreamingDataListener {
        void onDataReceived(String data);
    }
}
