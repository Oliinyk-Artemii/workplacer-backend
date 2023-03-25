package oliin.apps.workplacer.rest.feature.company;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.domain.feature.company.CreateCompany;
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

    private final CreateCompany createCompany;

    @PostMapping
    public ResponseEntity<CreateCompanyResponse> createCompany(@RequestBody @Valid CreateCompanyRequest request) {
        log.debug("Create company with name - {}", request.name());

        String companyId = createCompany.doCreateCompany(request.name());
        return ResponseEntity.status(HttpStatus.CREATED).body(new CreateCompanyResponse(companyId));
    }

    public record CreateCompanyRequest(@JsonProperty("name") @NotBlank String name) {
    }

    public record CreateCompanyResponse(@JsonProperty("company-id") String companyId) {
    }
}
