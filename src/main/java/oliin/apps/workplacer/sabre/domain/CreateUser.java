package oliin.apps.workplacer.sabre.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.auth.domain.model.UserModel;
import oliin.apps.workplacer.auth.domain.model.UserRole;
import oliin.apps.workplacer.registry.UserRegistry;
import oliin.apps.workplacer.sabre.domain.model.UserInfo;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateUser {
    private final UserRegistry userRegistry;

    public String doCreateUser(UserInfo userInfo, UserRole userRole) {
        final UserModel user = userRegistry.createUser(userInfo.email(), userInfo.password(),
                userRole, userInfo.firstName(), userInfo.lastName());
        return user.getId();
    }
}
