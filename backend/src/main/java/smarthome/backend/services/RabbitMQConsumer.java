package smarthome.backend.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smarthome.backend.config.RabbitMQConfig;
import smarthome.backend.models.SensorData;
import smarthome.backend.repositories.SensorDataRepository;
import java.time.LocalDateTime;

@Service
public class RabbitMQConsumer {

    @Autowired
    private SensorDataRepository repository;

    @RabbitListener(queues = RabbitMQConfig.TELEMETRY_QUEUE)
    public void receive(SensorData data) {
        data.setTimestamp(LocalDateTime.now());
        repository.save(data);
    }
}
