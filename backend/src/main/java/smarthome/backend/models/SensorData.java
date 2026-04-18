package pt.ua.deti.ies.g303.backend.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class SensorData {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sensor_id", nullable = false)
    private Sensor sensor; // Ligação direta ao metadado

    private Double valor;
    private LocalDateTime timestamp;
    
    // Getters e Setters
}