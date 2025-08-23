package br.com.levelupfinances.level_up_finances.exception;

import br.com.levelupfinances.level_up_finances.domain.user.auth.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidCategoryDataException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidCategoryData(InvalidCategoryDataException e) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                "INVALID_CATEGORY_DATA",
                e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleCategoryNotFound(CategoryNotFoundException e){
        ErrorResponseDTO error = new ErrorResponseDTO(
                "CATEGORY_NOT_FOUND",
                "Categoria não encontrada"
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserAlreadyExists(UserAlreadyExistsException e) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                "USER_ALREADY_EXISTS",
                "Este email já está cadastrado"
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseDTO> handleBadCredentials(BadCredentialsException e) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                "INVALID_CREDENTIALS",
                "Email ou senha incorretos"
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleUsernameNotFound(UsernameNotFoundException e){
        ErrorResponseDTO error = new ErrorResponseDTO(
                "USER_NOT_FOUND",
                "Usuário não encontrado"
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

}
