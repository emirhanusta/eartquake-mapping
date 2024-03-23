package backend.controller;

import backend.dto.EarthquakeRequest;
import backend.dto.EarthquakeResponse;
import backend.service.EarthquakeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/earthquakes")
@CrossOrigin
public class EarthquakeController {
    private final EarthquakeService earthquakeService;

    public EarthquakeController(EarthquakeService earthquakeService) {
        this.earthquakeService = earthquakeService;
    }

    @PostMapping
    public ResponseEntity<EarthquakeResponse> createEarthquake(@RequestBody EarthquakeRequest earthquakeRequest) {
        return ResponseEntity.ok(earthquakeService.createEarthquake(earthquakeRequest));
    }

    @GetMapping("/{seconds}/{magnitude}")
    public ResponseEntity<List<EarthquakeResponse>> getLastEarthquakes(@PathVariable int seconds, @PathVariable float magnitude) {
        return ResponseEntity.ok(earthquakeService.getLastEarthquakesByMagnitude(seconds, magnitude));
    }
}
