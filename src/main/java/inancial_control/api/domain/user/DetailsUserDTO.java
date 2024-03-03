package inancial_control.api.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


public record DetailsUserDTO(
        Long id,
        String name,
        String email

)
{
        public DetailsUserDTO(User user) {
                this(user.getId(), user.getName(), user.getEmail());
        }
}
