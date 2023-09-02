package ntfyJava.model;

public enum PRIORITY {
    MAX("Max", 5),
    HIGH("High", 4),
    DEFAULT("Default", 3),
    LOW("Low", 2),
    MIN("Min", 1);

    private final String name;
    private final int level;

    PRIORITY(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

}
