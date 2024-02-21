package inancial_control.api.controller;

import inancial_control.api.domain.user.DetailsUserDTO;
import inancial_control.api.domain.user.UpdateUserDTO;
import inancial_control.api.repository.UserRepository;
import inancial_control.api.domain.user.CreateUserDTO;
import inancial_control.api.domain.user.User;
import inancial_control.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid CreateUserDTO data){
        var user = service.create(data);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity details(@PathVariable Long id){
      var user =  service.details(id);
      return ResponseEntity.ok(user);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid UpdateUserDTO data) {
        var user =  service.update(data);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
       service.delete(id);
       return ResponseEntity.noContent().build();
    }
}
