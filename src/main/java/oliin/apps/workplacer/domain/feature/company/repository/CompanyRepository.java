package oliin.apps.workplacer.domain.feature.company.repository;

import oliin.apps.workplacer.domain.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    Optional<Company> findById(String email);

    Set<Company> findCompaniesByIdIn(Set<String> ids);
}
