package olii.apps.workplacer.user;

import olii.apps.workplacer.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    Optional<List<User>> findAllByOfficesContains(String officeId);
}
