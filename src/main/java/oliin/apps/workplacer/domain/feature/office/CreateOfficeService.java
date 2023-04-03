package oliin.apps.workplacer.domain.feature.office;

import lombok.RequiredArgsConstructor;
import oliin.apps.workplacer.domain.exception.UserMissingException;
import oliin.apps.workplacer.domain.feature.office.repository.CreateOfficeRepository;
import oliin.apps.workplacer.domain.feature.user.repository.UserRepository;
import oliin.apps.workplacer.domain.model.Office;
import oliin.apps.workplacer.domain.model.user.User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreateOfficeService {
    private final CreateOfficeRepository createOfficeRepository;
    private final UserRepository userRepository;

    public String doCreateOffice(String officeName, String email, String companyId) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.map(user -> {
            final Office office = createOfficeRepository.createOffice(officeName, user, companyId);
            return office.getId();
        }).orElseThrow(UserMissingException::new);
    }
}