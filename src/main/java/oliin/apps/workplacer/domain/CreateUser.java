package oliin.apps.workplacer.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.domain.model.UserModel;
import oliin.apps.workplacer.domain.model.UserRole;
import oliin.apps.workplacer.domain.model.UserInfo;
import oliin.apps.workplacer.domain.repository.CreateUserRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateUser {
    private final CreateUserRepository createUserRepository;

    public String doCreateUser(UserInfo userInfo, UserRole userRole) {

        final UserModel user = createUserRepository.createUser(userInfo.email(), userInfo.password(),
                userRole, userInfo.firstName(), userInfo.lastName(), userInfo.companyId(), userInfo.officeId());
        return user.getId();
    }
}
