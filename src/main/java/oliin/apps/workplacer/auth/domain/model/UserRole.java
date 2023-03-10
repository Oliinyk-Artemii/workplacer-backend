package oliin.apps.workplacer.auth.domain.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.io.Serializable;

@Getter
public enum UserRole implements Serializable {
    OFFICE_MANAGER("office-manager"),
    EMPLOYEE("employee");

    UserRole(String json) {
        this.json = json;
    }

    @JsonValue
    final String json;
}
