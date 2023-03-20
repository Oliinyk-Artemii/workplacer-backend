package oliin.apps.workplacer.domain.feature.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.domain.feature.user.model.CreateUserRequest;
import oliin.apps.workplacer.domain.feature.user.model.User;
import oliin.apps.workplacer.domain.feature.user.repository.CreateUserRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateUser {
    private final CreateUserRepository createUserRepository;

    public String doCreateUser(CreateUserRequest createUserRequest) {
        final User user = createUserRepository.createUser(createUserRequest);
        return user.getId();
    }
}
