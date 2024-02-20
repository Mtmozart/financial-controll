package inancial_control.api.controller;

import inancial_control.api.domain.user.DetailsUserDTO;
import inancial_control.api.domain.user.UpdateUserDTO;
import inancial_control.api.repository.UserRepository;
import inancial_control.api.domain.user.CreateUserDTO;
import inancial_control.api.domain.user.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid CreateUserDTO datas){
         var user = new User(datas);
            repository.save(user);
        return ResponseEntity.ok(new DetailsUserDTO(user));

    }
    @GetMapping("/{id}")
    public ResponseEntity details(@PathVariable Long id){
      var user =  repository.getReferenceById(id);
      return ResponseEntity.ok(new DetailsUserDTO(user));
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid UpdateUserDTO datas) {
        var user =  repository.getReferenceById(datas.id());
        user.update(datas);
        return ResponseEntity.ok(new DetailsUserDTO(user));
    }

@DeleteMapping("{id}")
@Transactional
public ResponseEntity delete(@PathVariable Long id) {
    var medico = repository.getReferenceById(id);
    medico.delete(id);
    return ResponseEntity.noContent().build();
}
}
