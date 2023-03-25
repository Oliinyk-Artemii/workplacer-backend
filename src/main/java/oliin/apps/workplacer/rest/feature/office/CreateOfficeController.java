package oliin.apps.workplacer.rest.feature.office;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.config.JwtAuthenticationFilter;
import oliin.apps.workplacer.config.JwtService;
import oliin.apps.workplacer.domain.feature.office.CreateOffice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/office")
@RequiredArgsConstructor
public class CreateOfficeController {

    private final CreateOffice createOffice;
    private final JwtService jwtService;

    @PostMapping
    public ResponseEntity<CreateOfficeResponse> createCompany(@RequestBody @Valid CreateOfficeRequest request, @RequestHeader(name = JwtAuthenticationFilter.AUTHORIZATION_HEADER) String token) {
        String email = jwtService.extractUsername(token.substring(JwtAuthenticationFilter.BEARER.length()));
        log.debug("Create office with name - {}, user - {}", request.name(), email);

        String officeId = createOffice.doCreateOffice(request.name(), email);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CreateOfficeResponse(officeId));
    }

    public record CreateOfficeRequest(@JsonProperty("name") @NotBlank String name) {
    }

    public record CreateOfficeResponse(@JsonProperty("office-id") String office) {
    }
}