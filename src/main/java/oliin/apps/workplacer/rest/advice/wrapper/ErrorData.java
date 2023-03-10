package oliin.apps.workplacer.rest.advice.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorData {
    @JsonProperty("remaining-attempts")
    private Integer remainingAttempts;
    @JsonProperty("retry-after-seconds")
    private Long retryAfterSeconds;
}
