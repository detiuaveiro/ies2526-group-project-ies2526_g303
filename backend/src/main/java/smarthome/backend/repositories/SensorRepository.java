package smarthome.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import smarthome.backend.models.*;;

public interface SensorRepository extends JpaRepository<Sensor, String> {
}
