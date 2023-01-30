package olii.apps.workplacer.user;

import lombok.RequiredArgsConstructor;
import olii.apps.workplacer.user.model.request.CreateUserRequest;
import olii.apps.workplacer.user.model.response.CreateUserResponse;
import olii.apps.workplacer.user.model.response.OfficeUsersResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity<CreateUserResponse> createUser(
            @RequestBody CreateUserRequest request
    ) {
        return ResponseEntity.ok(userService.createUser(request));
    }

    @GetMapping("/user")
    public ResponseEntity<OfficeUsersResponse> getAllUsers(@RequestParam String officeId) {
        return ResponseEntity.ok(userService.getOfficeUsers(officeId));
    }
}
