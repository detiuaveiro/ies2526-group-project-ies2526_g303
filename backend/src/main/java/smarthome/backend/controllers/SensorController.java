package smarthome.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import smarthome.backend.models.Sensor;
import smarthome.backend.models.SensorData;
import smarthome.backend.repositories.SensorRepository;
import smarthome.backend.repositories.SensorDataRepository;
import org.springframework.http.ResponseEntity;
import java.util.Optional;
import java.util.List;


@RestController // Diz ao Spring que isto é uma API REST (vai devolver JSON)
@RequestMapping("/api/sensores") // O endereço base da tua API
@CrossOrigin(origins = "*") // CRÍTICO: Se não meteres isto, o teu React vai bloquear os pedidos por causa de erros de CORS.
public class SensorController {

    // O Autowired injeta automaticamente os repositórios que vão à base de dados
    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private SensorDataRepository dataRepository;

    // 1. LER TODOS OS SENSORES (GET /api/sensores)
    @GetMapping
    public List<Sensor> getAllSensores() {
        return sensorRepository.findAll();
    }

    // 2. LER O HISTÓRICO DE UM SENSOR ESPECÍFICO (GET /api/sensores/{id}/leituras)
    @GetMapping("/{id}/leituras")
    public List<SensorData> getLeiturasDoSensor(@PathVariable String id) {
        return dataRepository.findBySensorId(id);
    }

    // LER UM SENSOR ESPECÍFICO (GET /api/sensores/{id})
    @GetMapping("/{id}")
    public ResponseEntity<Sensor> getSensorById(@PathVariable String id) {
        Optional<Sensor> sensor = sensorRepository.findById(id);
        if (sensor.isPresent()) {
            return ResponseEntity.ok(sensor.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 3. REGISTAR UM NOVO SENSOR (POST /api/sensores)
    // Precisas disto para o David conseguir criar sensores falsos no Postman para testar o sistema.
    @PostMapping
    public Sensor createSensor(@RequestBody Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sensor> updateSensor(@PathVariable String id, @RequestBody Sensor sensorDetails) {
        Optional<Sensor> sensorOptional = sensorRepository.findById(id);
        
        if (sensorOptional.isPresent()) {
            Sensor sensor = sensorOptional.get();
            
            // Atualiza os campos mutáveis do sensor
            if (sensorDetails.getTipo() != null) sensor.setTipo(sensorDetails.getTipo());
            if (sensorDetails.getDivisao() != null) sensor.setDivisao(sensorDetails.getDivisao());
            if (sensorDetails.getUnidade() != null) sensor.setUnidade(sensorDetails.getUnidade());
            
            Sensor updatedSensor = sensorRepository.save(sensor);
            return ResponseEntity.ok(updatedSensor);
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 se não existir
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSensor(@PathVariable String id) {
        Optional<Sensor> sensorOptional = sensorRepository.findById(id);
        
        if (sensorOptional.isPresent()) {
            sensorRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // Retorna 204 após sucesso
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 se não existir
        }
    }
}
