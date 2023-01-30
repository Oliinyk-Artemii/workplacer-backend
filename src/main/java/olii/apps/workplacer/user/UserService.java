package olii.apps.workplacer.user;

import lombok.RequiredArgsConstructor;
import olii.apps.workplacer.config.JwtService;
import olii.apps.workplacer.user.model.User;
import olii.apps.workplacer.user.model.request.CreateUserRequest;
import olii.apps.workplacer.user.model.response.CreateUserResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateUserResponse createUser(CreateUserRequest request) {
        var user = User.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .userType(request.getUserType())
                .companies(request.getCompanies())
                .offices(request.getOffices())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);
        return CreateUserResponse
                .builder()
                .id(user.getId())
                .build();
    }


//    public OfficeUsersResponse getAllUsers() {
//        final String password = RandomStringUtils.random(8, CHARACTERS);
//        final Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
//        if (userOptional.isPresent()) {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            request.getEmail(),
//                            request.getPassword()
//                    )
//            );
//            var user = userRepository.findByEmail(request.getEmail())
//                    .orElseThrow();
//            var jwtToken = jwtService.generateToken(user);
//            return AuthenticationResponse
//                    .builder()
//                    .token(jwtToken)
//                    .build();
//        }
//        return AuthenticationResponse.builder().token("null").build();
//    }
}
