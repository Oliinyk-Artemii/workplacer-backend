package oliin.apps.workplacer.auth.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.auth.domain.model.DeviceInfo;
import oliin.apps.workplacer.auth.domain.repo.AuthUserRepository;
import oliin.apps.workplacer.config.JwtService;
import oliin.apps.workplacer.auth.domain.exception.InvalidPasswordException;
import oliin.apps.workplacer.auth.domain.exception.UserMissingException;
import oliin.apps.workplacer.auth.domain.model.UserCredentials;
import oliin.apps.workplacer.auth.domain.model.UserModel;
import oliin.apps.workplacer.auth.domain.model.UserRole;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Supplier;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignIn {

    @Qualifier("DEVICE_INFO")
    private final Supplier<DeviceInfo> deviceInfoSupplier;
    private final JwtService jwtService;
    private final AuthUserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public String doSignIn(UserCredentials userCredentials, UserRole officeManager) {
        final Optional<UserModel> userOptional = userRepository.findByEmail(userCredentials.email());
        if (userOptional.isPresent()) {
            var user = userOptional.get();
            signIn(userCredentials);
            return jwtService.generateToken(user);
        } else {
            throw new UserMissingException();
        }
    }

    public void signIn(UserCredentials userCredential) {
        String email = userCredential.email();
        String password = userCredential.password();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            email,
                            password
                    )
            );
        } catch (AuthenticationException exception) {
            log.error("Invalid password for email {}", email);
            throw new InvalidPasswordException();
        }
    }
}
