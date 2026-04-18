package pt.ua.deti.ies.g303.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.ua.deti.ies.g303.backend.models.SensorData;

public interface SensorDataRepository extends JpaRepository<SensorData, Long> { }   