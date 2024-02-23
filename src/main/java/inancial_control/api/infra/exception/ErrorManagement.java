package inancial_control.api.infra.exception;

import inancial_control.api.domain.user.validations.ValidacaoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class ErrorManagement {

    @ExceptionHandler(Exception.class)
    public ResponseEntity managementError500(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " +ex.getLocalizedMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Requisição inválida: " + ex.getLocalizedMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity handleNoHandlerFoundException(NoHandlerFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recurso não encontrado: " + ex.getRequestURL());
    }
    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity managementRuleOfNegotiation(ValidacaoException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    private record DataErrorValidation(String field, String mensage) {
        public DataErrorValidation(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
