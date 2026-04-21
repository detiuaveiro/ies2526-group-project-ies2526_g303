package smarthome.backend.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smarthome.backend.config.RabbitMQConfig;
import smarthome.backend.models.SensorData;
import smarthome.backend.models.Sensor;
import smarthome.backend.repositories.SensorDataRepository;
import smarthome.backend.repositories.SensorRepository;
import java.time.LocalDateTime;

@Service
public class RabbitMQConsumer {

    @Autowired
    private SensorDataRepository repository;

    @Autowired
    private SensorRepository sensorRepository;

    @RabbitListener(queues = RabbitMQConfig.TELEMETRY_QUEUE)
    public void receive(SensorData data) {

        System.out.println("DEBUG: Recebi leitura do sensor: " + data.getSensor().getId() + " | Valor: " + data.getValor());

        String sensorId = data.getSensor().getId();

        Sensor sensor = sensorRepository.findById(sensorId)
            .orElseThrow(() -> new RuntimeException("Sensor não existe: " + sensorId));

        data.setSensor(sensor); // AGORA é entidade gerida
        data.setTimestamp(LocalDateTime.now());

        repository.save(data);
    }
}
