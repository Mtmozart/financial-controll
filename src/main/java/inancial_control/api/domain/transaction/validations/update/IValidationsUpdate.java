package inancial_control.api.domain.transaction.validations.update;


import inancial_control.api.domain.transaction.UpdateTransactionDTO;


public interface IValidationsUpdate {
    void validator(UpdateTransactionDTO data);
}
