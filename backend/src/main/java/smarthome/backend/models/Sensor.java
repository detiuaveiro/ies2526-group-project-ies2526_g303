package smarthome.backend.models;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDivisao() {
        return divisao;
    }

    public void setDivisao(String divisao) {
        this.divisao = divisao;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public List<SensorData> getLeituras() {
        return leituras;
    }

    public void setLeituras(List<SensorData> leituras) {
        this.leituras = leituras;
    }
    
}
