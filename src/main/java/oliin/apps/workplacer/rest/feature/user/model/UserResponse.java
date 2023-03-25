package oliin.apps.workplacer.rest.feature.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

// TODO move to separate endpoint
public record UserResponse(@JsonProperty("id") String id,
                           @JsonProperty("first-name") String firstName,
                           @JsonProperty("last-name") String lastName,
                           @JsonProperty("email") String email,
                           @JsonProperty("company-ids") List<String> companyIds,

                           @JsonProperty("office-ids") List<String> officeIds,
                           @JsonProperty("user-role") AuthorityType role

) {
}
