package oliin.apps.workplacer.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/")
@RequiredArgsConstructor
public class HealthCheckController {
    @GetMapping
    public ResponseEntity<HealthCheckResponse> getOfficeUsers() {
        log.debug("Health check triggered");

        return ResponseEntity.status(HttpStatus.CREATED).body(new HealthCheckResponse("ok"));
    }


    public record HealthCheckResponse(@JsonProperty("status") String defaultPassword) {
    }
}
