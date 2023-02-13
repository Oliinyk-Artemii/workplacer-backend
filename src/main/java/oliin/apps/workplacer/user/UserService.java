package oliin.apps.workplacer.user;

import lombok.RequiredArgsConstructor;
import oliin.apps.workplacer.user.model.User;
import oliin.apps.workplacer.user.model.request.CreateUserRequest;
import oliin.apps.workplacer.user.model.response.CreateUserResponse;
import oliin.apps.workplacer.user.model.response.OfficeUsersResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateUserResponse createUser(CreateUserRequest request) {
        var user = User.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .userType(request.getUserType())
                .companies(request.getCompanies())
                .offices(request.getOffices())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);
        return CreateUserResponse
                .builder()
                .id(user.getId())
                .build();
    }


    public OfficeUsersResponse getOfficeUsers(String officeId) {
        final String password = RandomStringUtils.random(8, CHARACTERS);
        final Optional<List<User>> users = userRepository.findAllByOfficesContains(officeId);

        if (users.isPresent()) {
            return OfficeUsersResponse
                    .builder()
                    .users(users.get())
                    .initialPassword(password)
                    .build();
        }
        return OfficeUsersResponse.builder().build();
    }
}
