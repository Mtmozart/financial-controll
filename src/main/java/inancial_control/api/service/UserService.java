package inancial_control.api.service;

import inancial_control.api.domain.user.CreateUserDTO;
import inancial_control.api.domain.user.DetailsUserDTO;
import inancial_control.api.domain.user.UpdateUserDTO;
import inancial_control.api.domain.user.User;
import inancial_control.api.domain.user.validations.updateValidators.IValidatorUser;
import inancial_control.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private List<IValidatorUser> validatorUser;
    public DetailsUserDTO create(CreateUserDTO data){


        var user = new User(data);
        repository.save(user);
        return new DetailsUserDTO(user);
    }

    public DetailsUserDTO details(Long id){
       var user =  repository.getReferenceById(id);
       return new DetailsUserDTO(user);
    }

    public DetailsUserDTO update(UpdateUserDTO data){
        var user =  repository.getReferenceById(data.id());
        validatorUser.forEach(v -> v.validator(data));

        user.update(data);
        return new DetailsUserDTO(user);
    }

    public void delete(Long id){
        var user = repository.getReferenceById(id);
        user.delete(id);
    }
}
