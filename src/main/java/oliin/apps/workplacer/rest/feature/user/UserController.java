package oliin.apps.workplacer.rest.feature.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.domain.feature.user.UserService;
import oliin.apps.workplacer.rest.feature.user.mapper.UserResponseMapper;
import oliin.apps.workplacer.rest.feature.user.model.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final UserResponseMapper userResponseMapper;

    @GetMapping
    @PreAuthorize("hasAnyRole('OFFICE_MANAGER', 'FLOOR_MANAGER')")
    public ResponseEntity<OfficeUserResponse> getOfficeUsers(@NotBlank(message = "Office id can't be blank")
                                                             @RequestParam(name = "office-id") String officeId) {
        log.debug("Get users of office with id - {}", officeId);

        final List<UserResponse> users = userService.doGetOfficeUsers(officeId).stream().map(userResponseMapper::toUserResponse).toList();
        final String randomPassword = userService.generatePassword();

        return ResponseEntity.status(HttpStatus.CREATED).body(new OfficeUserResponse(randomPassword, users));
    }

    public record OfficeUserResponse(@JsonProperty("default-password") String defaultPassword,
                                     @JsonProperty("users") List<UserResponse> users) {
    }
}
