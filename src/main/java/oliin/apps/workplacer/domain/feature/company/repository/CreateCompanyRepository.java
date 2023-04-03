package oliin.apps.workplacer.domain.feature.company.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.domain.exception.CompanyExistsException;
import oliin.apps.workplacer.domain.model.Company;
import oliin.apps.workplacer.domain.model.user.User;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateCompanyRepository {
    private final CompanyRepository companyRepository;


    public Company createCompany(String companyName, User user) {
        final Set<String> existingCompanyNames = user.getCompanies().stream().map(Company::getName).collect(Collectors.toSet());
        if (existingCompanyNames.contains(companyName)) {
            log.error("The company with name {} is already created", companyName);
            throw new CompanyExistsException("Company already exists");
        }

        var company = Company.builder()
                .name(companyName)
                .isActive(true) // TODO implement isActive
                .build();
        company.addUser(user);

        companyRepository.save(company);
        log.info("Created the company {}", companyName);
        return company;
    }
}