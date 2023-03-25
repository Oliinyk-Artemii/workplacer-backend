package oliin.apps.workplacer.domain.feature.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.config.JwtService;
import oliin.apps.workplacer.domain.model.user.CreateUserRequest;
import oliin.apps.workplacer.domain.model.user.User;
import oliin.apps.workplacer.domain.feature.user.repository.CreateUserRepository;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SignUpService {
    private final CreateUserRepository createUserRepository;
    private final JwtService jwtService;

    public String doSignUp(CreateUserRequest createUserRequest) {
        final User user = createUserRepository.createUser(createUserRequest);
        return jwtService.generateToken(user);
    }
}
