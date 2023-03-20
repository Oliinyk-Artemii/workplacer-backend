package oliin.apps.workplacer.rest.feature.company;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/company")
@RequiredArgsConstructor
public class CreateCompanyController {



    @PostMapping
    public ResponseEntity<CreateCompanyResponse> createUser(@RequestBody @Valid CreateCompanyRequest request) {
        log.debug("Create company with name - {}", request.officeName());

//        String officeId = createUser.doCreateUser(createUserMapper.toCreateUserRequest(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(new CreateCompanyResponse("userId"));
    }

    public record CreateCompanyRequest(@JsonProperty("name") String officeName) {
    }

    public record CreateCompanyResponse(@JsonProperty("office-id") String officeId) {
    }
}