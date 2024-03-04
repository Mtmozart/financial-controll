package inancial_control.api.domain.user;

import jakarta.validation.constraints.NotNull;

public record UpdateUserDTO(

        String name,

        String email,

        String password) {
}
