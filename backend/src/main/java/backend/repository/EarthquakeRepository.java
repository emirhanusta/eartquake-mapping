package backend.repository;

import backend.model.Earthquake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.UUID;

public interface EarthquakeRepository extends JpaRepository<Earthquake, UUID>{
    // query method to find earthquakes created after a given timestamp and with a magnitude greater than a given value
    @Query("SELECT e FROM Earthquake e WHERE e.created_at > ?1 AND e.magnitude >= ?2")
    Collection<Earthquake> findEarthquakesAfter(Timestamp timestamp, float magnitude);
}
