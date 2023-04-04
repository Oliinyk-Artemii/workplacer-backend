package oliin.apps.workplacer.feature.office.domain;

import lombok.RequiredArgsConstructor;
import oliin.apps.workplacer.domain.exception.UserMissingException;
import oliin.apps.workplacer.domain.repository.UserRepository;
import oliin.apps.workplacer.domain.model.Office;
import oliin.apps.workplacer.domain.model.user.User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OfficeService {
    private final UserRepository userRepository;

    public Set<Office> doGetUserOffices(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.map(User::getOffices).orElseThrow(UserMissingException::new);
    }
}