package inancial_control.api.service;

import inancial_control.api.domain.user.CreateUserDTO;
import inancial_control.api.domain.user.DetailsUserDTO;
import inancial_control.api.domain.user.UpdateUserDTO;
import inancial_control.api.domain.user.User;
import inancial_control.api.domain.user.validations.createValidators.IValidatorUserCreate;
import inancial_control.api.domain.user.validations.detailsValidators.IValidatorUserDetails;
import inancial_control.api.domain.user.validations.updateValidators.IValidatorUserUpdate;
import inancial_control.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository repository;
    @Autowired private List<IValidatorUserUpdate> validatorUserUpdates;
    @Autowired private List<IValidatorUserCreate> validatorUserCreates;
    @Autowired private List<IValidatorUserDetails> validatorUserDetails;
    public DetailsUserDTO create(CreateUserDTO data){
        var user = new User(data);
        user.encryptedPassword(user.getPassword());
        validatorUserCreates.forEach(v -> v.validator(data));
        repository.save(user);
        return new DetailsUserDTO(user);
    }

    public DetailsUserDTO details(Long id){
       var user =  repository.getReferenceById(id);
       validatorUserDetails.forEach(v -> v.validator(id));
       return new DetailsUserDTO(user);
    }

    public DetailsUserDTO update(UpdateUserDTO data, Long id){
        var user =  repository.getReferenceById(id);
        validatorUserUpdates.forEach(v -> v.validator(data));
        user.update(data);
        return new DetailsUserDTO(user);
    }

    public void delete(Long id){
        var user = repository.getReferenceById(id);
        user.delete(id);
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserDetails userDetails = repository.findByEmail(email);

        if (userDetails == null) {
            throw new UsernameNotFoundException("Usuário não encontrado com o login: " + email);
        }
        return userDetails;

    }
}
