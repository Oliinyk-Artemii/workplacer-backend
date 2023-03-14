package oliin.apps.workplacer.domain.repository;

import oliin.apps.workplacer.domain.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Integer> {
    Privilege findByName(String name);
}
