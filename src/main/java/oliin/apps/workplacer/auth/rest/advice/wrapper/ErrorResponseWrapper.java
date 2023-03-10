package oliin.apps.workplacer.auth.rest.advice.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseWrapper {

    private String error;
    private String details;
    private ErrorData data;

    public ErrorResponseWrapper(String error, String details) {
        this.error = error;
        this.details = details;
    }
}
