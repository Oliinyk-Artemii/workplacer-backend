package olii.apps.workplacer.auth;

import lombok.RequiredArgsConstructor;
import olii.apps.workplacer.config.JwtService;
import olii.apps.workplacer.user.model.User;
import olii.apps.workplacer.user.UserRepository;
import olii.apps.workplacer.user.model.UserType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .companies(new HashSet<>()) // TODO add companies
                .offices(new HashSet<>()) // TODO add offices
                .password(passwordEncoder.encode(request.getPassword()))
                .userType(UserType.OFFICE_MANAGER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }


    public AuthenticationResponse authenticate(RegisterRequest request) {
        final Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
        if (userOptional.isPresent()) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            var user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow();
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse
                    .builder()
                    .token(jwtToken)
                    .build();
        }
        return AuthenticationResponse.builder().token("null").build();
    }
}
