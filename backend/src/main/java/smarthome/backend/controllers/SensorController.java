package smarthome.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import smarthome.backend.models.Sensor;
import smarthome.backend.models.SensorData;
import smarthome.backend.repositories.SensorRepository;
import smarthome.backend.repositories.SensorDataRepository;

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

    // 3. REGISTAR UM NOVO SENSOR (POST /api/sensores)
    // Precisas disto para o David conseguir criar sensores falsos no Postman para testar o sistema.
    @PostMapping
    public Sensor createSensor(@RequestBody Sensor sensor) {
        return sensorRepository.save(sensor);
    }
}