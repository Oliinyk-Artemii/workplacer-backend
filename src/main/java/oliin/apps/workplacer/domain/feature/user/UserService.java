package oliin.apps.workplacer.domain.feature.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.domain.PasswordGenerator;
import oliin.apps.workplacer.domain.feature.user.model.User;
import oliin.apps.workplacer.domain.feature.user.repository.UserRepository;
import oliin.apps.workplacer.rest.feature.user.UserController;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordGenerator passwordGenerator;

    public UserController.OfficeUserResponse doGetOfficeUsers(String officeId) {
        final String password = passwordGenerator.generatePassword();
        final Optional<List<User>> users = userRepository.findAllByOfficeIdsContains(officeId);

        return users.map(
                        userModels -> new UserController.OfficeUserResponse(password, userModels))
                .orElseGet(() -> new UserController.OfficeUserResponse(password, List.of()));
    }
}
