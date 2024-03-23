package backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Earthquake {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id ;
    private float latitude;
    private float longitude;
    private float magnitude;
    @CreationTimestamp
    private Timestamp created_at;
}
