package oliin.apps.workplacer.feature.company.domain;

import lombok.RequiredArgsConstructor;
import oliin.apps.workplacer.domain.exception.UserMissingException;
import oliin.apps.workplacer.domain.repository.UserRepository;
import oliin.apps.workplacer.domain.model.Company;
import oliin.apps.workplacer.domain.model.user.User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final UserRepository userRepository;

    public Set<Company> doGetUserCompanies(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.map(User::getCompanies).orElseThrow(UserMissingException::new);
    }
}