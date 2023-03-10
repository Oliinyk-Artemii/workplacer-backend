package oliin.apps.workplacer.auth.rest.advice;

import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.auth.domain.exception.UserExistsException;
import oliin.apps.workplacer.auth.rest.RegisterController;
import oliin.apps.workplacer.auth.rest.advice.wrapper.ErrorResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(assignableTypes = {RegisterController.class})
public class RegisterControllerAdvice {
    @ExceptionHandler(UserExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponseWrapper userExists(UserExistsException exception) {
        log.error("Handling user exist exception for user registration: {}", exception.getMessage());
        return new ErrorResponseWrapper("user-exists", "The user with current msisdn already exists");
    }
}
