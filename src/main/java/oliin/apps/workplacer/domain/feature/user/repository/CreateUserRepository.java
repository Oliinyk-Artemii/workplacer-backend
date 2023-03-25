package oliin.apps.workplacer.domain.feature.user.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.domain.exception.UserExistsException;
import oliin.apps.workplacer.domain.feature.company.repository.CompanyRepository;
import oliin.apps.workplacer.domain.feature.office.repository.OfficeRepository;
import oliin.apps.workplacer.domain.model.Company;
import oliin.apps.workplacer.domain.model.Office;
import oliin.apps.workplacer.domain.model.user.CreateUserRequest;
import oliin.apps.workplacer.domain.model.user.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Supplier;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateUserRepository {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final OfficeRepository officeRepository;

    public User createUser(CreateUserRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            log.error("The user with email {} is already registered", request.getEmail());
            throw new UserExistsException("User already exists");
        }


        final Optional<List<Company>> companiesOptional = companyRepository.findCompaniesByIdIn(request.getCompanyIds().stream().toList());
        final List<Company> companies = companiesOptional.orElseGet((Supplier<? extends List<Company>>) new ArrayList<>());
        final Set<Office> offices = officeRepository.findOfficesByIdIn(request.getOfficeIds()).orElseGet(HashSet::new);

        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .roles(request.getAuthorities())
                .build();

        companies.forEach(company -> company.addUser(user));
        offices.forEach(office -> office.addUser(user));

        companyRepository.saveAll(companies);
        officeRepository.saveAll(offices);

        userRepository.save(user);
        log.info("Created the user {}", request.getEmail());
        return user;
    }
}
