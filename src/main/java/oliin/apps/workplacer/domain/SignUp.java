package oliin.apps.workplacer.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.domain.model.UserModel;
import oliin.apps.workplacer.domain.model.UserRegistrationInfo;
import oliin.apps.workplacer.domain.model.UserRole;
import oliin.apps.workplacer.domain.repository.CreateUserRepository;
import oliin.apps.workplacer.config.JwtService;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SignUp {
    private final CreateUserRepository createUserRepository;
    private final JwtService jwtService;

    public String doSignUp(UserRegistrationInfo userInfo, UserRole userRole) {
        final UserModel user = createUserRepository.createUser(userInfo.email(), userInfo.password(),
                userRole);
        return jwtService.generateToken(user);
    }
}
