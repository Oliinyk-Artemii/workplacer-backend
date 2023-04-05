package oliin.apps.workplacer.feature.user.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.feature.user.domain.RegisterService;
import oliin.apps.workplacer.feature.user.rest.mapper.CreateUserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/register")
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;
    private final CreateUserMapper createUserMapper;

    @PostMapping
    public ResponseEntity<RegisterResponse> signUp(@RequestBody @Valid RegisterController.RegisterRequest request) {
        log.debug("Start user registration with email - {}", request.email);

        String accessToken = registerService.doSignUp(createUserMapper.toCreateUserRequest(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(new RegisterResponse(accessToken));
    }

    public record RegisterRequest(@NotBlank(message = "Email can't be blank") String email,
                                  @NotBlank(message = "Password can't be blank") String password) {
    }

    public record RegisterResponse(@JsonProperty("access-token") String accessToken) {
    }
}
