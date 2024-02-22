package inancial_control.api.domain.user.validations.createValidators;

import inancial_control.api.domain.user.CreateUserDTO;
import inancial_control.api.domain.user.UpdateUserDTO;

public interface IValidatorUserCreate {
    void validator(CreateUserDTO data);

}
