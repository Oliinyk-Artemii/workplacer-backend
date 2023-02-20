package oliin.apps.workplacer.auth.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

//    public CreateUserResponse createUser(CreateUserRequest request) {
//        var user = User.builder()
//                .firstName(request.getFirstname())
//                .lastName(request.getLastname())
//                .email(request.getEmail())
//                .userType(request.getUserType())
//                .companies(request.getCompanies())
//                .offices(request.getOffices())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .build();
//        userRepository.save(user);
//        return CreateUserResponse
//                .builder()
//                .id(user.getId())
//                .build();
//    }


//    public OfficeUsersResponse getOfficeUsers(String officeId) {
//        final String password = RandomStringUtils.random(8, CHARACTERS);
//        final Optional<List<User>> users = userRepository.findAllByOfficesContains(officeId);
//
//        if (users.isPresent()) {
//            return OfficeUsersResponse
//                    .builder()
//                    .users(users.get())
//                    .initialPassword(password)
//                    .build();
//        }
//        return OfficeUsersResponse.builder().build();
//    }
}
