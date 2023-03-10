package oliin.apps.workplacer.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.domain.model.UserModel;
import oliin.apps.workplacer.domain.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
//    private final UserInfoMapper userInfoMapper;

    @GetMapping
    public ResponseEntity<OfficeUserResponse> getOfficeUsers(@NotBlank(message = "Office id can't be blank")
                                                             @RequestParam(name = "office-id") String officeId) {
        log.debug("Get users of office with id - {}", officeId);

        OfficeUserResponse response = userService.doGetOfficeUsers(officeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public record OfficeUserResponse(@JsonProperty("default-password") String defaultPassword,
                                     @JsonProperty("users") List<UserModel> users) {
    }
}
