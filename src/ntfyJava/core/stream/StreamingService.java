package ntfyJava.core.stream;

import ntfyJava.core.common.NtfyConstants;
import ntfyJava.core.exception.NtfyStreamingException;
import ntfyJava.core.model.StreamRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class StreamingService {
    private final String streamingUrl;
    private boolean running;
    private StreamingDataListener dataListener;

    public StreamingService(StreamRequest request) {
        this.streamingUrl = NtfyConstants.HTTPS + request.getHost() + "/" +
                request.getTopic() + "/" + NtfyConstants.JSON;
    }

    public void start() throws NtfyStreamingException{
        try {
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
                    System.err.println(e.getMessage());
                }
            });
            streamThread.start();
        } catch (Exception e) {
            throw new NtfyStreamingException(NtfyConstants.CONNECTION_ERROR_MSG, e);
        }

    }

    public void stop() {
        running = false;
    }

    public void setDataListener(StreamingDataListener listener) {
        this.dataListener = listener;
    }


}
