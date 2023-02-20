package oliin.apps.workplacer.auth.rest.advice;

import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.auth.domain.exception.InvalidPasswordException;
import oliin.apps.workplacer.auth.domain.exception.MaxLoginAttemptsReachedException;
import oliin.apps.workplacer.auth.domain.exception.UserMissingException;
import oliin.apps.workplacer.auth.rest.LoginController;
import oliin.apps.workplacer.auth.rest.advice.wrapper.ErrorData;
import oliin.apps.workplacer.auth.rest.advice.wrapper.ErrorResponseWrapper;
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
    public ErrorResponseWrapper invalidPassword(InvalidPasswordException exception) {
        log.error("Handling invalid user passcode for login");
        ErrorData errorData = new ErrorData();
        errorData.setRemainingAttempts(exception.getRemainingAttempts());
        return new ErrorResponseWrapper("invalid-passcode", "Invalid user's passcode", errorData);
    }

    @ExceptionHandler(MaxLoginAttemptsReachedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponseWrapper maxLoginAttemptsReached(MaxLoginAttemptsReachedException exception) {
        log.error("Handling max login attempts reached");
        ErrorData errorData = new ErrorData();
        errorData.setRetryAfterSeconds(exception.getRetryAfterSeconds());
        return new ErrorResponseWrapper("max-login-attempts-reached",
                "User has reached a limit of max login attempts", errorData);
    }
}