package oliin.apps.workplacer.user.model.request;

import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import oliin.apps.workplacer.user.model.UserType;

import java.util.List;

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
    private List<String> companies;
    @ElementCollection
    private List<String> offices;
}
