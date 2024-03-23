package backend.dto;

public record EarthquakeRequest(
        float latitude,
        float longitude,
        float magnitude
) {
}
