package oliin.apps.workplacer.feature.user.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.domain.model.user.CreateUserRequest;
import oliin.apps.workplacer.domain.model.user.User;
import oliin.apps.workplacer.domain.repository.CreateUserRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateUserService {
    private final CreateUserRepository createUserRepository;

    public String doCreateUser(CreateUserRequest createUserRequest) {
        final User user = createUserRepository.createUser(createUserRequest);
        return user.getId();
    }
}
