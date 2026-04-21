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

        if (sensorRepository.count() > 0) {
            System.out.println("Sensores já existem. Seed ignorado.");
            return;
        }

        List<Sensor> sensores = List.of(
            create("temperatura_001", "temperatura", "sala", "Celsius"),
            create("humidade_001", "humidade", "sala", "%"),
            create("luz_001", "luz", "corredor", "binary"),
            create("movimento_001", "movimento", "corredor", "binary"),
            create("energia_001", "energia", "casa", "kWh")
        );

        sensorRepository.saveAll(sensores);

        System.out.println("Seed de sensores concluído!");
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
