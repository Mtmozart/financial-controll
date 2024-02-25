package inancial_control.api.controller;

import inancial_control.api.domain.transaction.CreateTransactionDTO;
import inancial_control.api.domain.transaction.DetailsTransactionDTO;
import inancial_control.api.domain.transaction.MonthTransaction;
import inancial_control.api.domain.transaction.UpdateTransactionDTO;
import inancial_control.api.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("transactions")
public class TransactionController {

    @Autowired
    TransactionService service;

    @PostMapping
   public ResponseEntity create(@RequestBody @Valid CreateTransactionDTO data){
         var transaction = service.create(data);
         return ResponseEntity.ok(transaction);
    }
    @GetMapping("/{id}")
    public ResponseEntity details(@PathVariable Long id){
        var transaction =  service.details(id);
        return ResponseEntity.ok(transaction);
    }


    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody UpdateTransactionDTO data){
        var transaction =  service.update(id, data);
        return ResponseEntity.ok(transaction);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        var message =  service.delete(id);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity allTransactionsForUser(@PathVariable Long id){
        var transcations = service.allTransactionsForUser(id);
        if(transcations.isEmpty()){
            return ResponseEntity.ok("Nenhuma transação para o usuário");
        }
        return ResponseEntity.ok(Collections.unmodifiableList(transcations));
    }

    @GetMapping("/user/{id}/month/{month}")
    public ResponseEntity userTransactionsByMonth(@PathVariable Long id, @PathVariable MonthTransaction month){
        var transactions = service.userTransactionsByMonth(id, month);
        return ResponseEntity.ok(Collections.unmodifiableList(transactions));

    }
    @GetMapping("/user/{id}/month/{month}/entries")
    public ResponseEntity userTransactionsEntriesByMonth(@PathVariable Long id, @PathVariable MonthTransaction month){
        var transactions = service.userTransactionsEntriesByMonthByUser(id, month);
        return ResponseEntity.ok(Collections.unmodifiableList(transactions));

    }



}
