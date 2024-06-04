package ntfyJava.core.model;

import java.util.Base64;
import java.util.List;

/**
 * This class represents a message object for publishing/ sending notification message.
 * It contains various properties related to the structure of the notification.
 */

public class NtfyRequest {
    /**
     * The topic to which you are publishing the message.
     */
    private String topic;

    /**
     * The actual message content to be published. It can contain markdown as well.
     */
    private String message;

    /**
     * The host or source to which you are publishing.
     */
    private String host;

    /**
     * The title or subject of the message.
     */
    private String title;

    /**
     * The priority level of the message.
     */
    private PRIORITY priority;

    /**
     * A list of tags associated with the message.
     */
    private List<String> tags;

    /**
     * The URL associated with the message.
     */
    private String url;

    /**
     * Indicates whether the message content is in Markdown format.
     */
    private boolean isMarkdown;

    /**
     * A list of actions related to the message.
     */
    private List<Action> actions;

    /**
     * An attachment associated with the message. This will be the url linked of the attachment file.
     */
    private String attach;

    /**
     * This local file that needs to be attached with the message,
     */
    private String fileName;

    /**
     * The icon image url that needs to be used in the notification
     */
    private String icon;
    /**
     * The email address to which the notification is to be sent.
     */
    private String email;

    /**
     * The phone number to which the notification will be read to via call.
     */
    private String phone;

    /**
     * Access token, generated in ntfy CLI
     */
    private String accessToken;
    /**
     * Basic auth in format username:password
     */
    private String authToken;


    /**
     * Sets the auth token (Base64 of username:password)
     *
     * @param userCombo username/password combo for generation of access token
     */
    public void setAuthToken(String userCombo){
        this.authToken = Base64.getEncoder().encodeToString(userCombo.getBytes());
    }

    /**
     * Get the auth token to authenticate to Server
     *
     * @return auth token as String
     */
    public String getAuthToken(){
        return this.authToken;
    }


    /**
     * Get the accessToken for Authentication
     *
     * @return access token
     */
    public String getAccessToken(){
        return this.accessToken;
    }

    /**
     * @param accessToken The authToken from CLI
     */
    public void setAccessToken(String accessToken){
        this.accessToken = accessToken;
    }


    /**
     * Get the phone number to which the notification will be read via a call.
     *
     * @return The phone number as a String.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Set the phone number to which the notification will be read via a call.
     *
     * @param phone The phone number to set as a String.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }


    /**
     * Get the email address to which the notification is to be sent.
     *
     * @return The email address as a String.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email address to which the notification is to be sent.
     *
     * @param email The email address to set as a String.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the icon image URL that needs to be used in the notification.
     *
     * @return The icon image URL as a String.
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Set the icon image URL that needs to be used in the notification.
     *
     * @param icon The icon image URL to set as a String.
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }


    /**
     * Get the name of the local file that needs to be attached with the message.
     *
     * @return The file name as a String.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Set the name of the local file that needs to be attached with the message.
     *
     * @param fileName The file name to set as a String.
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    /**
     * Get the attachment url associated with the message.
     *
     * @return The attachment url as a String.
     */
    public String getAttach() {
        return attach;
    }

    /**
     * Set the attachment url for the message.
     *
     * @param attach The attachment url to set as a String.
     */
    public void setAttach(String attach) {
        this.attach = attach;
    }

    /**
     * Get the list of actions related to the message.
     *
     * @return A list of Action objects.
     */
    public List<Action> getActions() {
        return actions;
    }

    /**
     * Set the list of actions for the message.
     *
     * @param actions A list of Action objects to set.
     */
    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    /**
     * Check if the message content is in Markdown format.
     *
     * @return True if the message content is in Markdown, false otherwise.
     */
    public boolean isMarkdown() {
        return isMarkdown;
    }

    /**
     * Set whether the message content is in Markdown format.
     *
     * @param markdown True to set the message content as Markdown, false otherwise.
     */
    public void setMarkdown(boolean markdown) {
        isMarkdown = markdown;
    }

    /**
     * Get the URL associated with the message.
     *
     * @return The URL as a String.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Set the URL for the message.
     *
     * @param url The URL to set as a String.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Get the actual message content.
     *
     * @return The message content as a String.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the message content.
     *
     * @param message The message content to set as a String.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Get the host or source of the message.
     *
     * @return The host as a String.
     */
    public String getHost() {
        return host;
    }

    /**
     * Set the host or source of the message.
     *
     * @param host The host to set as a String.
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * Get the title or subject of the message.
     *
     * @return The title as a String.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the title or subject of the message.
     *
     * @param title The title to set as a String.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the priority level of the message.
     *
     * @return The priority level as a PRIORITY enum value.
     */
    public PRIORITY getPriority() {
        return priority;
    }

    /**
     * Set the priority level of the message.
     *
     * @param priority The priority level to set as a PRIORITY enum value.
     */
    public void setPriority(PRIORITY priority) {
        this.priority = priority;
    }

    /**
     * Get the list of tags associated with the message.
     *
     * @return A list of tags as Strings.
     */
    public List<String> getTags() {
        return tags;
    }

    /**
     * Set the list of tags associated with the message.
     *
     * @param tags A list of tags to set as Strings.
     */
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /**
     * Get the topic to which the message is published.
     *
     * @return The topic as a String.
     */
    public String getTopic() {
        return topic;
    }

    /**
     * Set the topic to which the message is published.
     *
     * @param topic The topic to set as a String.
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }
}

