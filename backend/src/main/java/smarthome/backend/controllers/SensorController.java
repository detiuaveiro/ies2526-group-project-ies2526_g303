package smarthome.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smarthome.backend.models.Sensor;
import smarthome.backend.models.SensorData;
import smarthome.backend.repositories.SensorRepository;
import smarthome.backend.repositories.SensorDataRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sensores")
@CrossOrigin(origins = "*")
public class SensorController {

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private SensorDataRepository dataRepository;

    @GetMapping
    public List<Sensor> getAllSensores() {
        return sensorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sensor> getSensorById(@PathVariable String id) {
        Optional<Sensor> sensor = sensorRepository.findById(id);
        if (sensor.isPresent()) {
            return ResponseEntity.ok(sensor.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/leituras")
    public List<SensorData> getLeiturasDoSensor(@PathVariable String id) {
        return dataRepository.findBySensorId(id);
    }

    @GetMapping("/{id}/leituras/paginado")
    public Page<SensorData> getLeiturasPaginadas(
            @PathVariable String id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return dataRepository.findBySensorId(id, PageRequest.of(page, size));
    }

    @PostMapping
    public Sensor createSensor(@RequestBody Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sensor> updateSensor(@PathVariable String id, @RequestBody Sensor sensorDetails) {
        Optional<Sensor> sensorOptional = sensorRepository.findById(id);
        
        if (sensorOptional.isPresent()) {
            Sensor sensor = sensorOptional.get();
            
            if (sensorDetails.getTipo() != null) sensor.setTipo(sensorDetails.getTipo());
            if (sensorDetails.getDivisao() != null) sensor.setDivisao(sensorDetails.getDivisao());
            if (sensorDetails.getUnidade() != null) sensor.setUnidade(sensorDetails.getUnidade());
            
            Sensor updatedSensor = sensorRepository.save(sensor);
            return ResponseEntity.ok(updatedSensor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSensor(@PathVariable String id) {
        Optional<Sensor> sensorOptional = sensorRepository.findById(id);
        
        if (sensorOptional.isPresent()) {
            sensorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}