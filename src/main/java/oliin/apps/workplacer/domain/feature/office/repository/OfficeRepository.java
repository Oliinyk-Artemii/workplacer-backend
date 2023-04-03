package oliin.apps.workplacer.domain.feature.office.repository;

import oliin.apps.workplacer.domain.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface OfficeRepository extends JpaRepository<Office, Integer> {
    Optional<Office> findById(String email);

    Set<Office> findOfficesByIdIn(Set<String> ids);
}

