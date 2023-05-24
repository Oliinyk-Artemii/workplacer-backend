package oliin.apps.workplacer.domain.model.user;

import oliin.apps.workplacer.feature.user.domain.model.AuthorityType;

import java.util.Set;

public interface UserProjection {
    String getId();
    String getFirstName();
    String getLastName();
    String getEmail();
    Set<AuthorityType> getRoles();
}
