package olii.apps.workplacer.user.model.request;

import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import olii.apps.workplacer.user.model.UserType;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private UserType userType;
    @ElementCollection
    private Set<String> companies;
    @ElementCollection
    private Set<String> offices;
}
