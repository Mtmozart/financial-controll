package inancial_control.api.user;

import jakarta.validation.constraints.*;
import org.springframework.context.annotation.Bean;


public record CreateUserDTO(

        @NotBlank
        String name,
        @NotBlank @Email
        String email,
        @NotBlank @Pattern(regexp = "^(?=.*[0-9])?(?=.*[@$!%*?&])?(?=.*[A-Z])?[A-Za-z\\d@$!%*?&]{5,}$")
        String password) {
}
