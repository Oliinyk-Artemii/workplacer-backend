package oliin.apps.workplacer.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.domain.model.CreateUserRequest;
import oliin.apps.workplacer.domain.model.User;
import oliin.apps.workplacer.domain.repository.CreateUserRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

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
