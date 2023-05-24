package oliin.apps.workplacer.domain.repository;

import oliin.apps.workplacer.domain.model.user.User;
import oliin.apps.workplacer.domain.model.user.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u.id as id, u.firstName as firstName, u.lastName as lastName, u.email as email, u.roles as roles FROM User u")
    Optional<List<UserProjection>> findAllByOfficesId(String officeId);

    Optional<User> findByEmail(String email);
}
