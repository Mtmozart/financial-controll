package inancial_control.api.domain.user;

import jakarta.validation.constraints.NotNull;

public record UpdateUserDTO(
        @NotNull
        Long id,
        String name,

        String email,

        String password) {
}
