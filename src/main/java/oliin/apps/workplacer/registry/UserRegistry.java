package oliin.apps.workplacer.registry;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.auth.domain.exception.UserExistsException;
import oliin.apps.workplacer.auth.domain.model.UserModel;
import oliin.apps.workplacer.auth.domain.model.UserRole;
import oliin.apps.workplacer.auth.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserRegistry {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    public UserModel createUser(String email, String password, UserRole role) {
        if (userRepository.findByEmail(email).isPresent()) {
            log.error("The user with email {} is already registered", email);
            throw new UserExistsException("User already exists");
        }

        var user = UserModel.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .userType(role)
                .firstName("")
                .lastName("")
                .companies(new LinkedList<>()) // TODO add companies
                .offices(new LinkedList<>()) // TODO add offices
                .userType(role)
                .build();

        userRepository.save(user);
        log.info("Created the user {}", email);
        return user;
    }

    public UserModel createUser(String email, String password, UserRole role, String firstName, String lastName) {
        if (userRepository.findByEmail(email).isPresent()) {
            log.error("The user with email {} is already registered", email);
            throw new UserExistsException("User already exists");
        }

        var user = UserModel.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .userType(role)
                .firstName(firstName)
                .lastName(lastName)
                .companies(new LinkedList<>()) // TODO add companies
                .offices(new LinkedList<>()) // TODO add offices
                .userType(role)
                .build();
//        UsersResource usersResource = usersResourceAccessor.getUsers();

        userRepository.save(user);
        log.info("Created the user {}", email);
        return user;

    }
}
