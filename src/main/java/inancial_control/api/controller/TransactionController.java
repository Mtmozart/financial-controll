package inancial_control.api.controller;

import inancial_control.api.domain.transaction.CreateTransactionDTO;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transactions")
public class TransactionController {

    @PostMapping
    @Transactional
    public void create(@RequestBody CreateTransactionDTO data){
        System.out.println(data);
    }
}
