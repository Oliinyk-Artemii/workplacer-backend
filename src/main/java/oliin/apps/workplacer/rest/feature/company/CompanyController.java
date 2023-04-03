package oliin.apps.workplacer.rest.feature.company;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.config.JwtAuthenticationFilter;
import oliin.apps.workplacer.config.JwtService;
import oliin.apps.workplacer.domain.feature.company.CompanyService;
import oliin.apps.workplacer.rest.feature.company.mapper.CompanyResponseMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;
    private final CompanyResponseMapper companyResponseMapper;
    private final JwtService jwtService;

    @GetMapping
    public ResponseEntity<List<CompanyResponse>> getUserCompanies(@RequestHeader(name = JwtAuthenticationFilter.AUTHORIZATION_HEADER) String token) {
        String email = jwtService.extractUsername(token.substring(JwtAuthenticationFilter.BEARER.length()));
        log.debug("Get companies with user - {}", email);

        final List<CompanyResponse> companies = companyService.doGetUserCompanies(email).stream().map(companyResponseMapper::toCompanyResponse).toList();

        return ResponseEntity.status(HttpStatus.CREATED).body(companies);
    }

    public record CompanyResponse(@JsonProperty("id") String id,
                                  @JsonProperty("name") String name,
                                  @JsonProperty("active") boolean isActive) {
    }
}
