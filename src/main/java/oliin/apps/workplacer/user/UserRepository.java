package oliin.apps.workplacer.user;

import oliin.apps.workplacer.domain.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
    Optional<UserModel> findByEmail(String email);

    Optional<List<UserModel>> findAllByOfficesContains(String officeId);
}
