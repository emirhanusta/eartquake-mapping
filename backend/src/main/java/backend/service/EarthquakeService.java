package backend.service;

import backend.dto.EarthquakeRequest;
import backend.dto.EarthquakeResponse;
import backend.model.Earthquake;
import backend.repository.EarthquakeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@Slf4j
public class EarthquakeService {
    private final EarthquakeRepository earthquakeRepository;

    public EarthquakeService(EarthquakeRepository earthquakeRepository) {
        this.earthquakeRepository = earthquakeRepository;
    }

    public EarthquakeResponse createEarthquake(EarthquakeRequest earthquakeRequest) {
        var earthquake = Earthquake.builder()
                .latitude(earthquakeRequest.latitude())
                .longitude(earthquakeRequest.longitude())
                .magnitude(earthquakeRequest.magnitude())
                .build();
        log.info("Saving earthquake: {}", earthquakeRequest);
        return EarthquakeResponse.from(earthquakeRepository.save(earthquake));
    }

    public List<EarthquakeResponse> getLastEarthquakesByMagnitude(int seconds, float magnitude) {
        var timestamp = new Timestamp(System.currentTimeMillis() - seconds * 1000L);
        log.info("Getting earthquakes created after: {} and magnitude greater than: {}", timestamp, magnitude);
        return earthquakeRepository.findEarthquakesAfter(timestamp, magnitude)
                .stream()
                .map(EarthquakeResponse::from)
                .toList();
    }
}
