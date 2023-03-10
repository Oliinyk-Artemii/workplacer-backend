package oliin.apps.workplacer.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.domain.model.UserModel;
import oliin.apps.workplacer.domain.repository.UserRepository;
import oliin.apps.workplacer.rest.UserController;
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
        final Optional<List<UserModel>> users = userRepository.findAllByOfficesContains(officeId);

        return users.map(
                        userModels -> new UserController.OfficeUserResponse(password, userModels))
                .orElseGet(() -> new UserController.OfficeUserResponse(password, List.of()));
    }
}
