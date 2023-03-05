package oliin.apps.workplacer.auth.domain.repo;

import oliin.apps.workplacer.auth.domain.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<UserModel, Integer> {
    Optional<UserModel> findByEmail(String email);
}
