package oliin.apps.workplacer.rest;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.domain.SignUp;
import oliin.apps.workplacer.rest.mapper.UserAuthenticationMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static oliin.apps.workplacer.domain.model.UserRole.OFFICE_MANAGER;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/user/register")
@RequiredArgsConstructor
public class RegisterController {

    private final SignUp signUp;
    private final UserAuthenticationMapper userAuthenticationMapper;
//    private final DeviceTokenService deviceTokenService;

    @PostMapping
    public ResponseEntity<RegisterResponse> signUp(@RequestBody @Valid RegisterController.RegisterRequest request) {
        log.debug("Start user registration with email - {}", request.email);

        String accessToken = signUp.doSignUp(userAuthenticationMapper.toUserInfo(request), OFFICE_MANAGER);
        return ResponseEntity.status(HttpStatus.CREATED).body(new RegisterResponse(accessToken));
    }

    public record RegisterRequest(@NotBlank(message = "Email can't be blank") String email,
                                  @NotBlank(message = "Password can't be blank") String password,
                                  @JsonAlias("first-name") String firstName,
                                  @JsonAlias("last-name") String lastName) {
    }

    public record RegisterResponse(@JsonProperty("access-token") String accessToken) {
    }
}
