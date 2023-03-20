package oliin.apps.workplacer.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

// TODO move to separate endpoint
public record UserResponse(@JsonProperty("id") String id,
                           @JsonProperty("first-name") String firstName,
                           @JsonProperty("last-name") String lastName,
                           @JsonProperty("email") String email,
                           @JsonProperty("company-ids") Set<String> companyIds,

                           @JsonProperty("office-ids") Set<String> officeIds,
                           @JsonProperty("user-role") AuthorityType role

) {
}
