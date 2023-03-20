package oliin.apps.workplacer.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.domain.SignIn;
import oliin.apps.workplacer.domain.model.User;
import oliin.apps.workplacer.rest.mapper.UserResponseMapper;
import oliin.apps.workplacer.rest.mapper.UserSessionMapper;
import oliin.apps.workplacer.rest.model.AuthorityType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/login")
@RequiredArgsConstructor
public class LoginController {

    private final SignIn signIn;
    private final UserSessionMapper userSessionMapper;
    private final UserResponseMapper userResponseMapper;
//    private final DeviceTokenService deviceTokenService;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        log.debug("Login user with email - {}", request.email());

        User user = signIn.doSignIn(userSessionMapper.toUserCredentials(request));
        String accessToken = signIn.generateUserAccessToken(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new LoginResponse(accessToken, userResponseMapper.toUserResponse(user)));
    }

    public record LoginRequest(@NotBlank(message = "Email can't be blank") String email,
                               @NotBlank(message = "Password can't be blank") String password) {
    }

    public record LoginResponse(@JsonProperty("access-token") String accessToken,
                                @JsonProperty("user-model") UserResponse userResponse) {
    }

    // TODO move to separate endpoint
    public record UserResponse(@JsonProperty("id") String id,
                               @JsonProperty("first-name") String firstName,
                               @JsonProperty("last-name") String lastName,
                               @JsonProperty("email") String email,
                               @JsonProperty("company-ids") Set<String> companyIds,

                               @JsonProperty("office-ids") Set<String> officeIds,
                               @JsonProperty("user-role") AuthorityType role

    ) {
    }
}
