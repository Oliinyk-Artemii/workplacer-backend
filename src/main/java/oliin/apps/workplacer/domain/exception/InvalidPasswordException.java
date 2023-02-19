package oliin.apps.workplacer.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class InvalidPasswordException extends RuntimeException {
    private final int remainingAttempts;
}
