package backend.service;

import backend.dto.EarthquakeRequest;
import backend.dto.EarthquakeResponse;
import backend.model.Earthquake;
import backend.repository.EarthquakeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EarthquakeServiceTest {

    private EarthquakeRepository earthquakeRepository;

    private EarthquakeService earthquakeService;

    @BeforeEach
    void setUp() {
        earthquakeRepository = mock(EarthquakeRepository.class);
        earthquakeService = new EarthquakeService(earthquakeRepository);
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
        when(earthquakeRepository.save(any())).thenReturn(earthquake);

        // Act
        EarthquakeResponse response = earthquakeService.createEarthquake(request);

        // Assert
        assertEquals(request.latitude(), response.latitude());
        assertEquals(request.longitude(), response.longitude());
        assertEquals(request.magnitude(), response.magnitude());
        verify(earthquakeRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Get last earthquakes by magnitude for the last hour")
    void testGetLastEarthquakesByMagnitude() {
        // Arrange
        int seconds = 3600; // 1 hour
        float magnitude = 5.0f;
        Earthquake e1= Earthquake.builder()
                .latitude(40.7f)
                .longitude(74.0f)
                .magnitude(5.5f)
                .build();
        Earthquake e2= Earthquake.builder()
                .latitude(40.7f)
                .longitude(74.0f)
                .magnitude(5.5f)
                .build();
        List<Earthquake> earthquakes = List.of(e1, e2);
        when(earthquakeRepository.findEarthquakesAfter(any(), anyFloat())).thenReturn(earthquakes);

        // Act
        List<EarthquakeResponse> response = earthquakeService.getLastEarthquakesByMagnitude(seconds, magnitude);

        // Assert
        assertEquals(earthquakes.size(), response.size());
        verify(earthquakeRepository, times(1)).findEarthquakesAfter(any(), anyFloat());
    }

}
