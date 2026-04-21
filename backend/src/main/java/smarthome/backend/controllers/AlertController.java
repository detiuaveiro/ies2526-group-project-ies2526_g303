package smarthome.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smarthome.backend.models.Alert;
import smarthome.backend.repositories.AlertRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alerts")
@CrossOrigin(origins = "*")
public class AlertController {

    @Autowired
    private AlertRepository alertRepository;

    @GetMapping
    public List<Alert> getAll() {
        return alertRepository.findAll();
    }

    @GetMapping("/unread")
    public List<Alert> getUnread() {
        return alertRepository.findByIsReadFalse();
    }

    @PostMapping
    public Alert create(@RequestBody Alert alert) {
        return alertRepository.save(alert);
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<Alert> markAsRead(@PathVariable Long id) {
        Optional<Alert> alertOptional = alertRepository.findById(id);
        if (alertOptional.isPresent()) {
            Alert alert = alertOptional.get();
            alert.setRead(true);
            return ResponseEntity.ok(alertRepository.save(alert));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}