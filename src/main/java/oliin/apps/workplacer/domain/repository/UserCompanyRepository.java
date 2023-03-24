package oliin.apps.workplacer.domain.repository;

import oliin.apps.workplacer.domain.model.UserCompany;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface UserCompanyRepository extends JpaRepository<UserCompany, Integer> {
    Optional<Set<UserCompany>> findUserCompaniesByCompanyIdIn(Set<String> ids);
}