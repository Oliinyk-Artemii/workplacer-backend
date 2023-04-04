package oliin.apps.workplacer.feature.user.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.config.JwtService;
import oliin.apps.workplacer.domain.exception.InvalidPasswordException;
import oliin.apps.workplacer.domain.exception.UserMissingException;
import oliin.apps.workplacer.domain.model.user.User;
import oliin.apps.workplacer.domain.model.user.UserCredentials;
import oliin.apps.workplacer.domain.repository.UserRepository;
import oliin.apps.workplacer.domain.model.DeviceInfo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Supplier;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignInService {

    @Qualifier("DEVICE_INFO")
    private final Supplier<DeviceInfo> deviceInfoSupplier;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public User doSignIn(UserCredentials userCredentials) {
        final Optional<User> userOptional = userRepository.findByEmail(userCredentials.email());
        return userOptional.map((user) -> {
            signIn(userCredentials);
            return user;


        }).orElseThrow(UserMissingException::new);
    }

    public String generateUserAccessToken(User user) {
        return jwtService.generateToken(user);
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
