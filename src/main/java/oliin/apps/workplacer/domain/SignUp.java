package oliin.apps.workplacer.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.config.JwtService;
import oliin.apps.workplacer.domain.exception.UserRoleMissingException;
import oliin.apps.workplacer.domain.model.Role;
import oliin.apps.workplacer.domain.model.UserModel;
import oliin.apps.workplacer.domain.model.UserRegistrationInfo;
import oliin.apps.workplacer.rest.model.UserRole;
import oliin.apps.workplacer.domain.repository.CreateUserRepository;
import oliin.apps.workplacer.domain.repository.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class SignUp {
    private final CreateUserRepository createUserRepository;
    private final JwtService jwtService;
    private final RoleRepository roleRepository;

    public String doSignUp(UserRegistrationInfo userInfo, String role) {

        Optional<Role> roleOptional = roleRepository.findByName(role);

        return roleOptional.map((userRole) -> {
            final UserModel user = createUserRepository.createUser(userInfo.email(), userInfo.password(),
                    userRole);
            return jwtService.generateToken(user);
        }).orElseThrow(UserRoleMissingException::new);
    }
}
