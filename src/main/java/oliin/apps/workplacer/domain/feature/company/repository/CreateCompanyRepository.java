package oliin.apps.workplacer.domain.feature.company.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.domain.feature.company.model.Company;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateCompanyRepository {
    private final CompanyRepository companyRepository;


    public Company createCompany(String companyName) {
//        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
//            log.error("The user with email {} is already registered", request.getEmail());
//            throw new UserExistsException("User already exists");
//        }

        var company = Company.builder()
                .name(companyName)
                .isActive(true) // TODO implement isActive
                .build();

        companyRepository.save(company);
        log.info("Created the company {}", companyName);
        return company;
    }
}