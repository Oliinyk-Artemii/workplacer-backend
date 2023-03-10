package oliin.apps.workplacer.auth.domain.model;

import jakarta.validation.constraints.NotBlank;

public record DeviceInfo(@NotBlank(message = "Device ID can't be blank") String id,
                         @NotBlank(message = "Device OS can't be blank") String os
        /*@Pattern(regexp = "^en$|^az$|^ru$", message = "Must be one of: [en]") String language*/) {
}
