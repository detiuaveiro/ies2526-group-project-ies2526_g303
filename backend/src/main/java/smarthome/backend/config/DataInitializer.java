package smarthome.backend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import smarthome.backend.models.Sensor;
import smarthome.backend.repositories.SensorRepository;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final SensorRepository sensorRepository;

    public DataInitializer(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    public void run(String... args) {
        if (sensorRepository.count() > 0) return;

        List<Sensor> sensores = List.of( 
            create("temp-sala-01", "temperatura", "sala", "Celsius"), 
            create("energia-cozinha-01", "energia", "cozinha", "kWh"),
            create("temp-quarto-bebe", "temperatura", "quarto_bebe", "Celsius")
        );
        sensorRepository.saveAll(sensores);

        System.out.println("DEBUG: Seed de sensores (temp-sala-01, etc.) concluído com sucesso!");
    }

    private Sensor create(String id, String tipo, String divisao, String unidade) {
        Sensor s = new Sensor();
        s.setId(id);
        s.setTipo(tipo);
        s.setDivisao(divisao);
        s.setUnidade(unidade);
        return s;
    }
}