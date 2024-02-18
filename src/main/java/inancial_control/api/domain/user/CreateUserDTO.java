package inancial_control.api.domain.user;

import jakarta.validation.constraints.*;
import org.springframework.context.annotation.Bean;


public record CreateUserDTO(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "^^(?=.*[a-z])(?=.*[\\d@$!%*?&])[A-Za-z\\d@$!%*?&]{5,}$")
        String password
)
{
}
