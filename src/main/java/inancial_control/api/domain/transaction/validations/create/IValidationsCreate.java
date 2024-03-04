package inancial_control.api.domain.transaction.validations.create;

import inancial_control.api.domain.transaction.CreateTransactionDTO;


public interface IValidationsCreate {
    void validator(CreateTransactionDTO data);
}
