package inancial_control.api.repository;

import inancial_control.api.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailContainingIgnoreCase(String email);

    UserDetails findByEmail(String email);

    @Query("SELECT u.active FROM User u WHERE u.email = :email")
    boolean findUserEmailIifsActive(String email);

    @Query("SELECT u.active FROM User u WHERE u.id = :id")
    boolean findUserIdlIifsActive(Long id);
}
