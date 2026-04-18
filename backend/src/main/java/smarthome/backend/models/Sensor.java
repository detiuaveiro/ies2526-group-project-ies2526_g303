package pt.ua.deti.ies.g303.backend.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Sensor {
    @Id
    private String id; // Ex: "S-123"
    private String tipo; // Ex: "movimento", "temperatura", "humidade"
    private String divisao; // Ex: "quarto_bebe"
    private String unidade; // Ex: "binary", "Celsius", "%"

    @OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL)
    private List<SensorData> leituras;

    public Sensor() {}
    // Getters e Setters
}