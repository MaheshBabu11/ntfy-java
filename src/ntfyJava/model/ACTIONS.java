package ntfyJava.model;

public enum ACTIONS {

    VIEW("view"),
    BROADCAST("broadcast"),
    HTTP("http");

    private final String name;

    ACTIONS(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
