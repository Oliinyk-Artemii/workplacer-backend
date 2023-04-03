package oliin.apps.workplacer.rest.feature.company;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.config.JwtAuthenticationFilter;
import oliin.apps.workplacer.config.JwtService;
import oliin.apps.workplacer.domain.feature.company.CreateCompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/company")
@RequiredArgsConstructor
public class CreateCompanyController {

    private final CreateCompanyService createCompanyService;
    private final JwtService jwtService;

    @PostMapping
    public ResponseEntity<CreateCompanyResponse> createCompany(@RequestBody @Valid CreateCompanyRequest request, @RequestHeader(name = JwtAuthenticationFilter.AUTHORIZATION_HEADER) String token) {
        String email = jwtService.extractUsername(token.substring(JwtAuthenticationFilter.BEARER.length()));
        log.debug("Create company with name - {}, user - {}", request.name(), email);

        String companyId = createCompanyService.doCreateCompany(request.name(), email);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CreateCompanyResponse(companyId));
    }

    public record CreateCompanyRequest(@JsonProperty("name") @NotBlank String name) {
    }

    public record CreateCompanyResponse(@JsonProperty("company-id") String companyId) {
    }
}
