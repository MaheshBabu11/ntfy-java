
# Ntfy Java  <img align="right" src="https://github.com/binwiederhier/ntfy/blob/main/web/public/static/images/ntfy.png">

[![Maven Package](https://github.com/MaheshBabu11/Excel2DataMap/actions/workflows/maven-publish.yml/badge.svg)](https://github.com/MaheshBabu11/Excel2DataMap/actions/workflows/maven-publish.yml)

Java library for publishing/receiving messages from a [ntfy](https://github.com/binwiederhier/ntfy) server.

ntfy (pronounce: notify) is a simple HTTP-based pub-sub notification service. It allows you to send notifications to your 
phone or desktop via scripts from any computer, entirely without signup, cost or setup. It’s also open source if you want 
to run your own. Visit ntfy.sh for more details.

ntfy-java is a Java wrapper for this service. The workflow is to replicate the GET/POST calls to ntfy-based servers and
provide a neat way to create a client/streaming service to send/receive notifications.

## Usage

## Prerequisites
###  Adding the dependency
```xml
    <dependency>
        <groupId>com.github.maheshbabu11</groupId>
        <artifactId>ntfy.java</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </dependency>
````
## Pub/Sub
### 1. Creating a new Client
    
    PubClient client = new NtfyClient(ClientType.PUB).getClient();

Here, the client type can be either `PUB` or `SUB` depending on the type of client you want to create.
Currently, the library supports only `PUB` client.

### 2. Creating the message as a JSON object

    NtfyRequest request = new NtfyRequest();

Here NtfyRequest is a POJO class that represents the JSON message that is sent to the ntfy server.
There are multiple fields that can be used to customize the message.

* `host` - The host of the ntfy server. Default is `ntfy.sh`.
* `topic` - The topic to which the message is to be published. (Required)
* `title` - The title of the message. (Required)
* `message` - The message body. (Required)
* `priority` - The priority of the message. PRIORITY enum can be used to set the priority. Default is `PRIORITY.NORMAL`.
* `attach` - The attachment to be sent along with the message. (Optional)
* `fileName` - The name of the attachment file. (Optional)
* `icon` - The icon to be used for the notification. (Optional)
* `email` - The email address to which the notification is to be sent. (Optional). This email will get a notification 
  with the message body and the attachment.
* `phone` - You can use ntfy to call a phone and read the message out loud using text-to-speech. Similar to email notifications, 
this can be useful to blast-notify yourself on all possible channels, or to notify people that do not have the ntfy app installed on their phone.
Phone numbers have to be previously verified (via the web app), so this feature is only available to authenticated users.
* `tags` - The list of tags that can be used in the message. (Optional). The detailed list of tags are available [here.](https://docs.ntfy.sh/publish/#tags-emojis)
* `markdown` - The message supports the use of markdowns. (Optional). Default is `false`.
* `Actions` - This can be used to create a button in the notification to perform an action when clicked. (Optional).
  ```
  Action action = new Action();
  action.setAction(ACTIONS.VIEW);
  action.setLabel("Open github");
  action.setUrl("https://github.com/MaheshBabu11/ntfy-java");
  action.setClear(true);
  ```
  The Action field is used to select the type of action using the ACTIONS enum.The ACTIONS enum has the following values:
  * `ACTIONS.VIEW` - Opens the url in the browser.
  * `ACTIONS.BROADCAST` - The broadcast action sends an Android broadcast intent when the action button is tapped. This allows integration into automation apps such as MacroDroid or Tasker.
  * `ACTIONS.HTTP` -The http action sends a HTTP request when the action button is tapped. You can use this to trigger REST APIs for whatever systems you have, e.g. opening the garage door, or turning on/off lights.

  To know more about the Actions, visit [here.](https://docs.ntfy.sh/publish/#action-buttons)
### 3. Sending the message

    client.sendNotification(request);

This will send the message to the ntfy server and the message will be published to the topic.

## Example usage

```java
package ntfyJava.example;


import ntfyJava.NtfyClient;
import ntfyJava.core.publish.PubClient;
import ntfyJava.core.exception.NtfyException;
import ntfyJava.core.model.*;

import java.util.ArrayList;
import java.util.List;

public class PublishExample {

  public static void main(String[] args) throws NtfyException {
    PubClient client = new NtfyClient(ClientType.PUB).getClient();
    NtfyRequest request = new NtfyRequest();
    request.setTopic("test_ntfy");
    request.setMessage("Look ma, **bold text**, *italics*, ...");
    request.setTitle("This is the obj msg");
    request.setPriority(PRIORITY.MAX);
    request.setAttach("https://media.licdn.com/dms/image/D4E03AQEZTNXuX3kG7g/profile-displayphoto-shrink_400_400/0/1669618932666?e=1699488000&v=beta&t=q2z_UDFvwTZa02SligKZqgwk66BjuXQZxWtQF_K1Jqw");
    request.setFileName("Screenshot.png");
    request.setIcon("https://styles.redditmedia.com/t5_32uhe/styles/communityIcon_xnt6chtnr2j21.png");
    request.setEmail("mahesh.b.pec@gmail.com");
    request.setPhone("");

    Action action = new Action();
    action.setAction(ACTIONS.VIEW);
    action.setLabel("Open github");
    action.setUrl("https://github.com/MaheshBabu11/ntfy-java");
    action.setClear(true);

    List<Action> actions = new ArrayList<>(List.of(action));
    List<String> tags = new ArrayList<>(List.of("+1", "warning"));
    request.setTags(tags);
    request.setMarkdown(true);
    request.setActions(actions);
    client.sendNotification(request);
  }
}

```
---
## Streaming

### 1. Creating a streaming request

    StreamRequest request = new StreamRequest();
Here, the StreamRequest is a POJO class that represents the JSON message that is sent to the ntfy server.
There are basically two fields in the request.
- `host` - The host of the ntfy server. Default is `ntfy.sh`.(Required)
- `topic` - The topic to which the message is to be published. (Required)

### 2. Creating a streaming service

    StreamingService streamingConnection = new StreamingService(request);
This will create a streaming service to receive notifications from the ntfy server.
We are using this connection to create a streaming service to receive notifications from the ntfy server.

### 3. Starting and listening to the streaming service

     streamingConnection.setDataListener(data -> {
            // Process the incoming data here
            System.out.println("Received data: " + data);
        });

This is a listener that is used to listen to the incoming data from the ntfy server.Here you can write the
logic to process the incoming data.

    streamingConnection.start();

This will start the streaming service and the listener will be invoked when a new message is received.

### 4. Stopping the streaming service

    streamingConnection.stop();

This will stop the streaming service.
### Example usage

```java
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
      streamingConnection.stop();
    }
}
```
---

## Exception Handling

The library throws three types of exceptions.
* `NtfyException` - This exception is thrown when there is an error in the request or the response from the ntfy server.
* `NtfyStreamingException` - This exception is thrown when there is an error in the streaming service.
* `NtfyConnectionException` - This exception is thrown when there is an error in connecting to the ntfy server.



## Features in development

* Support for SUB client. This includes a streaming service to receive notifications from the ntfy server (In Progress).
* Support for advanced features like access tokens, authentication, etc.

## More Info

To know more about ntfy and to refer the official docs, visit [here.](https://docs.ntfy.sh/)
