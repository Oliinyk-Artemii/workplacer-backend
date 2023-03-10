package oliin.apps.workplacer.rest;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.domain.model.UserRole;
import oliin.apps.workplacer.domain.CreateUser;
import oliin.apps.workplacer.rest.mapper.UserInfoMapper;
import oliin.apps.workplacer.rest.mapper.UserRoleDeserializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping(path = "/api/v1/user")
@RequiredArgsConstructor
public class CreateUserController {
    private final CreateUser createUser;
    private final UserInfoMapper userInfoMapper;

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest request) {
        log.debug("Create user with email - {}", request.email());

        String userId = createUser.doCreateUser(userInfoMapper.toUserInfo(request), request.userRole());
        return ResponseEntity.status(HttpStatus.CREATED).body(new CreateUserResponse(userId));
    }

    public record CreateUserRequest(@JsonProperty("first-name") String firstName,
                                    @JsonProperty("last-name") String lastName,
                                    @NotBlank(message = "Email can't be blank") String email,
                                    @NotBlank(message = "Password can't be blank") String password,
                                    @NotBlank(message = "Company id can't be blank")
                                    @JsonProperty("company-id") String companyId,
                                    @NotBlank(message = "Office id can't be blank")
                                    @JsonProperty("office-id") String officeId,
                                    @NotNull
                                    @JsonProperty("user-role")
                                    @JsonDeserialize(using = UserRoleDeserializer.class) UserRole userRole) {
    }

    public record CreateUserResponse(@JsonProperty("user-id") String userId) {
    }
}
