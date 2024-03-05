package inancial_control.api.infra.exception;

import inancial_control.api.domain.user.validations.ValidacaoException;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class ErrorManagement {

    @ExceptionHandler(Exception.class)
    public ResponseEntity managementError500(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageError("Erro: " + ex.getLocalizedMessage()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity managementError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity managementError400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream()
                .map(DataErrorValidation::new).toList());
    }

    private record DataErrorValidation(String field, String message) {
        private DataErrorValidation(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
    @ExceptionHandler(ValidacaoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity handleErrorBusinessRule(ValidacaoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageError(ex.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageError("Requisição inválida: " + ex.getLocalizedMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity runTimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageError("Requisição inválida: " + ex.getLocalizedMessage()));
    }


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity badCredentialsException(BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageError("Credenciais inválidas"));
    }
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity badRequest(BadRequestException ex) {
        return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity tratarErroAuthentication() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageError("Falha na autenticação"));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity handleNoHandlerFoundException(NoHandlerFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageError("Recurso não encontrado: " + ex.getRequestURL()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity errorAccesDenied() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new MessageError("Acesso negado"));
    }


}
