package oliin.apps.workplacer.auth.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.auth.domain.SignIn;
import oliin.apps.workplacer.auth.domain.model.UserModel;
import oliin.apps.workplacer.auth.rest.mapper.UserSessionMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static oliin.apps.workplacer.auth.domain.model.UserRole.OFFICE_MANAGER;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/login")
@RequiredArgsConstructor
public class LoginController {

    private final SignIn signIn;
    private final UserSessionMapper userSessionMapper;
//    private final DeviceTokenService deviceTokenService;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        log.debug("Login user with email - {}", request.email());

        UserModel user = signIn.doSignIn(userSessionMapper.toUserCredentials(request), OFFICE_MANAGER);
        String accessToken = signIn.generateUserAccessToken(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new LoginResponse(accessToken, user));
    }

    public record LoginRequest(@NotBlank(message = "Email can't be blank") String email,
                               @NotBlank(message = "Password can't be blank") String password) {
    }

    public record LoginResponse(@JsonProperty("access-token") String accessToken,
                                @JsonProperty("user-model") UserModel user) {
    }
}
