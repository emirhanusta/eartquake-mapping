package backend.controller;

import backend.dto.EarthquakeRequest;
import backend.dto.EarthquakeResponse;
import backend.model.Earthquake;
import backend.service.EarthquakeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class EarthquakeControllerTest {

    @Mock
    private EarthquakeService earthquakeService;

    @InjectMocks
    private EarthquakeController earthquakeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateEarthquake() {
        // Arrange
        EarthquakeRequest request = new EarthquakeRequest(40.7128f, -74.0060f, 5.5f);
        Earthquake earthquake = Earthquake.builder()
                .latitude(request.latitude())
                .longitude(request.longitude())
                .magnitude(request.magnitude())
                .build();
        EarthquakeResponse response = EarthquakeResponse.from(earthquake);
        when(earthquakeService.createEarthquake(request)).thenReturn(response);
        // Act
        ResponseEntity<EarthquakeResponse> result = earthquakeController.createEarthquake(request);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    void testGetLastEarthquakes() {
        // Arrange
        int seconds = 3600;
        float magnitude = 5.0f;
        List<Earthquake> responses = List.of(
                Earthquake.builder().latitude(40.7f).longitude(74.0f).magnitude(5.5f).build(),
                Earthquake.builder().latitude(37.7f).longitude(122.4f).magnitude(6.0f).build()
        );
        when(earthquakeService.getLastEarthquakesByMagnitude(seconds, magnitude)).thenReturn(
                List.of(EarthquakeResponse.from(responses.get(0)), EarthquakeResponse.from(responses.get(1))
                ));

        // Act
        ResponseEntity<List<EarthquakeResponse>> result = earthquakeController.getLastEarthquakes(seconds, magnitude);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(responses.size(), Objects.requireNonNull(result.getBody()).size());
    }
}
