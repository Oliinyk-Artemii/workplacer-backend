package oliin.apps.workplacer.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.domain.exception.InvalidPasswordException;
import oliin.apps.workplacer.domain.exception.UserMissingException;
import oliin.apps.workplacer.domain.model.DeviceInfo;
import oliin.apps.workplacer.domain.model.UserCredentials;
import oliin.apps.workplacer.domain.model.UserModel;
import oliin.apps.workplacer.domain.model.UserRole;
import oliin.apps.workplacer.config.JwtService;
import oliin.apps.workplacer.domain.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public UserModel doSignIn(UserCredentials userCredentials, UserRole officeManager) {
        final Optional<UserModel> userOptional = userRepository.findByEmail(userCredentials.email());
        if (userOptional.isPresent()) {
            var user = userOptional.get();
            signIn(userCredentials);
            return user;
        } else {
            throw new UserMissingException();
        }
    }

    public  String generateUserAccessToken(UserModel user) {
        return  jwtService.generateToken(user);
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
