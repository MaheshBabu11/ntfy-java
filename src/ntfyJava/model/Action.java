package ntfyJava.model;

public class Action {
    private ACTIONS action;
    private String label;
    private String url;
    private boolean clear;
    private String body;

    public ACTIONS getAction() {
        return action;
    }

    public void setAction(ACTIONS action) {
        this.action = action;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isClear() {
        return clear;
    }

    public void setClear(boolean clear) {
        this.clear = clear;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
