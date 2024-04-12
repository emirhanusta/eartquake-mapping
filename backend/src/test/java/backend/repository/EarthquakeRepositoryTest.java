package backend.repository;

import backend.model.Earthquake;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EarthquakeRepositoryTest {

    @Mock
    private EarthquakeRepository earthquakeRepository;

    @Test
    void testFindEarthquakesAfter() {
        // Arrange
        float magnitude = 5.0f;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        List<Earthquake> expectedEarthquakes = new ArrayList<>();
        expectedEarthquakes.add(Earthquake.builder().latitude(40.7f).longitude(74.0f).magnitude(5.5f).build());

        when(earthquakeRepository.findEarthquakesAfter(timestamp, magnitude)).thenReturn(expectedEarthquakes);

        // Act
        Collection<Earthquake> result = earthquakeRepository.findEarthquakesAfter(timestamp, magnitude);

        // Assert
        assertEquals(expectedEarthquakes.size(), result.size());
        assertEquals(expectedEarthquakes.get(0).getLatitude(), result.iterator().next().getLatitude());
    }
}
