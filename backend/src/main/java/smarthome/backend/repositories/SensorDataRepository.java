package smarthome.backend.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import smarthome.backend.models.*;
import java.util.List;

public interface SensorDataRepository extends JpaRepository<SensorData, Long> {
    List<SensorData> findBySensorId(String sensorId);
 }   