package oliin.apps.workplacer.domain.feature.office.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.domain.exception.CompanyMissingException;
import oliin.apps.workplacer.domain.exception.OfficeExistsException;
import oliin.apps.workplacer.domain.feature.company.repository.CompanyRepository;
import oliin.apps.workplacer.domain.model.Company;
import oliin.apps.workplacer.domain.model.Office;
import oliin.apps.workplacer.domain.model.user.User;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateOfficeRepository {
    private final OfficeRepository officeRepository;
    private final CompanyRepository companyRepository;

    public Office createOffice(String officeName, User user, String companyId) {
        final Set<String> existingOfficeNames = user.getOffices().stream().map(Office::getName).collect(Collectors.toSet());
        if (existingOfficeNames.contains(officeName)) {
            log.error("The office with name {} is already created", officeName);
            throw new OfficeExistsException("Office already exists");
        }
        Optional<Company> companyOptional = companyRepository.findById(companyId);
        if (companyOptional.isEmpty()) {
            log.error("The company with such id {} is missing", companyId);
            throw new CompanyMissingException("Company is missing");
        }

        var office = Office.builder()
                .name(officeName)
                .isActive(true) // TODO implement isActive
                .company(companyOptional.get())
                .build();
        office.addUser(user);

        officeRepository.save(office);
        log.info("Created the office {}", officeName);
        return office;
    }
}
