package inancial_control.api.controller;

import inancial_control.api.domain.user.DetailsUserDTO;
import inancial_control.api.domain.user.UpdateUserDTO;
import inancial_control.api.repository.UserRepository;
import inancial_control.api.domain.user.CreateUserDTO;
import inancial_control.api.domain.user.User;
import inancial_control.api.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid CreateUserDTO data, UriComponentsBuilder uriBuilder){
        var user = service.create(data);
        var uri = uriBuilder.path("users/{id}").buildAndExpand(user.id()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name="bearer-key")
    public ResponseEntity details(@PathVariable Long id){
      var user =  service.details(id);
      return ResponseEntity.ok(user);
    }

    @PutMapping("{id}")
    @Transactional
    @SecurityRequirement(name="bearer-key")
    public ResponseEntity update(@RequestBody @Valid UpdateUserDTO data, @PathVariable Long id) {
        var user =  service.update(data, id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("{id}")
    @Transactional
    @SecurityRequirement(name="bearer-key")
    public ResponseEntity delete(@PathVariable Long id) {
       service.delete(id);
       return ResponseEntity.noContent().build();
    }
}
