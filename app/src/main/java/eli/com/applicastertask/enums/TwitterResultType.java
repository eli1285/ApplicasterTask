package eli.com.applicastertask.enums;

/**
 * Created by eli on 22/12/16.
 */

public enum TwitterResultType {
    RECENT("recent"),
    POPULAR("popular"),
    MIXED("mixed"),
    FILTERED("filtered");

    final String name;

    TwitterResultType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
