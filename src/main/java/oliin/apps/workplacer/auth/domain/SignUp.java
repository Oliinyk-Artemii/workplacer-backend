package oliin.apps.workplacer.auth.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.auth.domain.model.UserModel;
import oliin.apps.workplacer.auth.domain.model.UserRegistrationInfo;
import oliin.apps.workplacer.auth.domain.model.UserRole;
import oliin.apps.workplacer.registry.UserRegistry;
import oliin.apps.workplacer.config.JwtService;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SignUp {
    private final UserRegistry userRegistry;
    private final JwtService jwtService;

    public String doSignUp(UserRegistrationInfo userInfo, UserRole userRole) {
        final UserModel user = userRegistry.createUser(userInfo.email(), userInfo.password(),
                userRole);
        return jwtService.generateToken(user);
    }
}
