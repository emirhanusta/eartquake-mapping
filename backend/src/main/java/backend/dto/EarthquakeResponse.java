package backend.dto;

import backend.model.Earthquake;

import java.util.UUID;

public record EarthquakeResponse(
        UUID id,
        float latitude,
        float longitude,
        float magnitude
) {
    public static EarthquakeResponse from(Earthquake earthquake) {
        return new EarthquakeResponse(
                earthquake.getId(),
                earthquake.getLatitude(),
                earthquake.getLongitude(),
                earthquake.getMagnitude()
        );
    }
}
