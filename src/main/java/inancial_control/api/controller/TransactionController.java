package inancial_control.api.controller;

import inancial_control.api.domain.transaction.CreateTransactionDTO;
import inancial_control.api.domain.transaction.DetailsTransactionDTO;
import inancial_control.api.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
