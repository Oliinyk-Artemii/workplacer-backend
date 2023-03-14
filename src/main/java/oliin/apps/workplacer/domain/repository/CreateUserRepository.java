package oliin.apps.workplacer.domain.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.domain.exception.UserExistsException;
import oliin.apps.workplacer.domain.model.Role;
import oliin.apps.workplacer.domain.model.UserModel;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedList;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateUserRepository {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    public UserModel createUser(String email, String password, Role role) {
        if (userRepository.findByEmail(email).isPresent()) {
            log.error("The user with email {} is already registered", email);
            throw new UserExistsException("User already exists");
        }

        var user = UserModel.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
//                .userType(role)
                .firstName("")
                .lastName("")
                .roles(Arrays.asList(role))
                .companies(new LinkedList<>()) // TODO add companies
                .offices(new LinkedList<>()) // TODO add offices
                .build();

        userRepository.save(user);
        log.info("Created the user {}", email);
        return user;
    }

    public UserModel createUser(String email, String password, Role role, String firstName, String lastName, String companyId, String officeId) {
        if (userRepository.findByEmail(email).isPresent()) {
            log.error("The user with email {} is already registered", email);
            throw new UserExistsException("User already exists");
        }
        LinkedList<String> companyIds = new LinkedList<>();
        LinkedList<String> officeIds = new LinkedList<>();
        companyIds.add(companyId);
        officeIds.add(officeId);

        var user = UserModel.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .roles(Arrays.asList(role))
                .firstName(firstName)
                .lastName(lastName)
                .companies(companyIds)
                .offices(officeIds)
                .build();

        userRepository.save(user);
        log.info("Created the user {}", email);
        return user;

    }
}
