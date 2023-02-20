package oliin.apps.workplacer.auth.user;

import oliin.apps.workplacer.auth.domain.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
    Optional<UserModel> findByEmail(String email);

    Optional<List<UserModel>> findAllByOfficesContains(String officeId);
}
