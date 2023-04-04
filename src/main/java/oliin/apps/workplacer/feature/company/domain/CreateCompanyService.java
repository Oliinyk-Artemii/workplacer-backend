package oliin.apps.workplacer.feature.company.domain;

import lombok.RequiredArgsConstructor;
import oliin.apps.workplacer.domain.exception.UserMissingException;
import oliin.apps.workplacer.domain.repository.CreateCompanyRepository;
import oliin.apps.workplacer.domain.repository.UserRepository;
import oliin.apps.workplacer.domain.model.Company;
import oliin.apps.workplacer.domain.model.user.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreateCompanyService {
    private final CreateCompanyRepository createCompanyRepository;
    private final UserRepository userRepository;

    public String doCreateCompany(String companyName, String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.map(user -> {
            final Company company = createCompanyRepository.createCompany(companyName, user);
            return company.getId();
        }).orElseThrow(UserMissingException::new);
    }
}

