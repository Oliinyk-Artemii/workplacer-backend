package oliin.apps.workplacer.rest.feature.user.advice;

import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.domain.exception.InvalidPasswordException;
import oliin.apps.workplacer.domain.exception.UserMissingException;
import oliin.apps.workplacer.rest.feature.user.LoginController;
import oliin.apps.workplacer.rest.advice.wrapper.ErrorResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(assignableTypes = {LoginController.class})
public class LoginControllerAdvice {

    @ExceptionHandler(UserMissingException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponseWrapper userMissing() {
        log.error("Handling user missing exception for login");
        return new ErrorResponseWrapper("user-missing", "The user with current msisdn does not exists");
    }

    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponseWrapper invalidPassword() {
        log.error("Handling invalid user passcode for login");
        return new ErrorResponseWrapper("invalid-passcode", "Invalid user's passcode");
    }
}