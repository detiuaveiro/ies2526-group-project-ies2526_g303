package smarthome.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import smarthome.backend.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
}