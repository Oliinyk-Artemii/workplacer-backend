package oliin.apps.workplacer.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.domain.model.User;
import oliin.apps.workplacer.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordGenerator passwordGenerator;

    public List<User> doGetOfficeUsers(String officeId) {
        final Optional<List<User>> users = userRepository.findAllByOfficeIdsContains(officeId);

        return users.orElseGet(List::of);
    }

    public String generatePassword() {
        return passwordGenerator.generatePassword();
    }
}
