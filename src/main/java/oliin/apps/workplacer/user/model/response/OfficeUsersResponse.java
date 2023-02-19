package oliin.apps.workplacer.user.model.response;

import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import oliin.apps.workplacer.user.model.User;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OfficeUsersResponse {
    private String initialPassword;
    @ElementCollection
    private List<User> users;
}
