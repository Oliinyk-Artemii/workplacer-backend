package oliin.apps.workplacer.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.domain.exception.UserRoleMissingException;
import oliin.apps.workplacer.domain.model.Role;
import oliin.apps.workplacer.domain.model.UserInfo;
import oliin.apps.workplacer.domain.model.UserModel;
import oliin.apps.workplacer.domain.repository.CreateUserRepository;
import oliin.apps.workplacer.domain.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateUser {
    private final CreateUserRepository createUserRepository;
    private final RoleRepository roleRepository;

    public String doCreateUser(UserInfo userInfo, String role) {
        Optional<Role> optionalRole = roleRepository.findByName(role);

        return optionalRole.map((userRole) -> {
            final UserModel user = createUserRepository.createUser(userInfo.email(), userInfo.password(),
                    userRole, userInfo.firstName(), userInfo.lastName(), userInfo.companyId(), userInfo.officeId());
            return user.getId();
        }).orElseThrow(UserRoleMissingException::new);
    }
}
