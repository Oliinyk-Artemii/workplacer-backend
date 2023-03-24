package oliin.apps.workplacer.domain.feature.user.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.domain.feature.company.model.Company;
import oliin.apps.workplacer.domain.feature.company.repository.CompanyRepository;
import oliin.apps.workplacer.domain.feature.user.exception.UserExistsException;
import oliin.apps.workplacer.domain.feature.user.model.CreateUserRequest;
import oliin.apps.workplacer.domain.feature.user.model.User;
import oliin.apps.workplacer.domain.model.UserCompany;
import oliin.apps.workplacer.domain.model.UserCompanyId;
import oliin.apps.workplacer.domain.repository.UserCompanyRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateUserRepository {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserCompanyRepository userCompanyRepository;
    private final CompanyRepository companyRepository;


    public User createUser(CreateUserRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            log.error("The user with email {} is already registered", request.getEmail());
            throw new UserExistsException("User already exists");
        }

        Optional<Set<Company>> companies = companyRepository.findCompaniesByIdIn(request.getCompanyIds());

        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .roles(request.getAuthorities())
                .companies(new HashSet<>())
                .officeIds(request.getOfficeIds())
                .build();
        companies.ifPresent(companySet -> companySet.forEach(user::addCompany));

        userRepository.save(user);
        log.info("Created the user {}", request.getEmail());
        return user;
    }

    private UserCompany createUserCompany(User user, Company company) {
        return UserCompany
                .builder()
                .company(company)
                .user(user)
                .build();
    }
}
