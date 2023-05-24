package oliin.apps.workplacer.feature.user.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserProjectionResponse(@JsonProperty("id") String id,
                                     @JsonProperty("first-name") String firstName,
                                     @JsonProperty("last-name") String lastName,
                                     @JsonProperty("email") String email,
                                     @JsonProperty("user-role") AuthorityType role) {
}
