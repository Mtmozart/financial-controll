package inancial_control.api.repository;

import inancial_control.api.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailContainingIgnoreCase(String email);

    UserDetails findByEmail(String email);
}
