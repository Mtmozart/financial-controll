package inancial_control.api.controller;

import inancial_control.api.domain.transaction.CreateTransactionDTO;
import inancial_control.api.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transactions")
public class TransactionController {

    @Autowired
    TransactionService service;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid CreateTransactionDTO data){
         var transaction = service.create(data);
         return ResponseEntity.ok(transaction);
    }
    @GetMapping("/{id}")
    public ResponseEntity details(@PathVariable Long id){
        var transaction =  service.details(id);
        return ResponseEntity.ok(transaction);
    }

}
