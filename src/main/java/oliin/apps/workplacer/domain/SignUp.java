package oliin.apps.workplacer.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.config.JwtService;
import oliin.apps.workplacer.domain.model.UserInfo;
import oliin.apps.workplacer.domain.model.UserModel;
import oliin.apps.workplacer.domain.model.UserRole;
import oliin.apps.workplacer.security.UserRegistry;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SignUp {
    private final UserRegistry userRegistry;
    private final JwtService jwtService;

    public String doSignUp(UserInfo userInfo, UserRole userRole) {
        final UserModel user = userRegistry.createUser(userInfo.email(), userInfo.password(),
                userRole, userInfo.firstName(), userInfo.lastName());
        return jwtService.generateToken(user);
    }
}
