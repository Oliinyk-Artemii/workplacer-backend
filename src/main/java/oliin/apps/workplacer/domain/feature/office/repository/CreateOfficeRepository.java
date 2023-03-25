package oliin.apps.workplacer.domain.feature.office.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.domain.exception.OfficeExistsException;
import oliin.apps.workplacer.domain.model.Office;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateOfficeRepository {
    private final OfficeRepository officeRepository;

    public Office createOffice(String officeName, Set<String> existingOfficeNames) {
        if (existingOfficeNames.contains(officeName)) {
            log.error("The office with name {} is already created", officeName);
            throw new OfficeExistsException("Office already exists");
        }

        var office = Office.builder()
                .name(officeName)
                .isActive(true) // TODO implement isActive
                .build();

        officeRepository.save(office);
        log.info("Created the office {}", officeName);
        return office;
    }
}
