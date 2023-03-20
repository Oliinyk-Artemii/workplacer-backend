package oliin.apps.workplacer.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import oliin.apps.workplacer.rest.model.AuthorityType;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<AuthorityType> authorities;
    private Set<String> companyIds;
    private Set<String> officeIds;
}
