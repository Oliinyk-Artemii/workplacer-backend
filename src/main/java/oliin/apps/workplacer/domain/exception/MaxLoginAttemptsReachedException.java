package oliin.apps.workplacer.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MaxLoginAttemptsReachedException extends RuntimeException {
    private final long retryAfterSeconds;
}
