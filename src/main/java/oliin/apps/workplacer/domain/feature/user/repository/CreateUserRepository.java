package oliin.apps.workplacer.domain.feature.user.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.domain.feature.user.exception.UserExistsException;
import oliin.apps.workplacer.domain.feature.user.model.CreateUserRequest;
import oliin.apps.workplacer.domain.feature.user.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateUserRepository {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    public User createUser(CreateUserRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            log.error("The user with email {} is already registered", request.getEmail());
            throw new UserExistsException("User already exists");
        }

        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .roles(request.getAuthorities())
                .companyIds(request.getCompanyIds())
                .officeIds(request.getOfficeIds())
                .build();

        userRepository.save(user);
        log.info("Created the user {}", request.getEmail());
        return user;
    }
}
