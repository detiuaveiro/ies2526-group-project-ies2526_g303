package smarthome.backend.models;

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
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Sensor getSensor() {
        return sensor;
    }
    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
    
}
