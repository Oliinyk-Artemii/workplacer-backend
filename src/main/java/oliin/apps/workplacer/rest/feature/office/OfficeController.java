package oliin.apps.workplacer.rest.feature.office;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.config.JwtAuthenticationFilter;
import oliin.apps.workplacer.config.JwtService;
import oliin.apps.workplacer.domain.feature.office.OfficeService;
import oliin.apps.workplacer.rest.feature.office.mapper.OfficeResponseMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/office")
@RequiredArgsConstructor
public class OfficeController {
    private final OfficeService officeService;
    private final OfficeResponseMapper officeResponseMapper;
    private final JwtService jwtService;

    @GetMapping
    public ResponseEntity<List<OfficeResponse>> getUserCompanies(@RequestHeader(name = JwtAuthenticationFilter.AUTHORIZATION_HEADER) String token) {
        String email = jwtService.extractUsername(token.substring(JwtAuthenticationFilter.BEARER.length()));
        log.debug("Get offices with user - {}", email);

        final List<OfficeResponse> offices = officeService.doGetUserOffices(email).stream().map(officeResponseMapper::toOfficeResponse).toList();

        return ResponseEntity.status(HttpStatus.CREATED).body(offices);
    }

    public record OfficeResponse(@JsonProperty("id") String id,
                                 @JsonProperty("name") String name,
                                 @JsonProperty("active") boolean isActive,
                                 @JsonProperty("company-id") String companyId) {
    }
}
