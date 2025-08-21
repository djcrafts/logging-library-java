package logger.model;

/**
 * Marker for alert log records. Can be extended for custom alert types.
 */
public record AlertMarker(String type) {

    @Override
    public String toString() {
        return "AlertMarker{" +
                "type='" + type + '\'' +
                '}';
    }
}
