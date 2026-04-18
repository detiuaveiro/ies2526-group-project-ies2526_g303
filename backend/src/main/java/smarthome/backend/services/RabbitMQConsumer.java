package pt.ua.deti.ies.g303.backend.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ua.deti.ies.g303.backend.config.RabbitMQConfig;
import pt.ua.deti.ies.g303.backend.models.SensorData;
import pt.ua.deti.ies.g303.backend.repositories.SensorDataRepository;
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