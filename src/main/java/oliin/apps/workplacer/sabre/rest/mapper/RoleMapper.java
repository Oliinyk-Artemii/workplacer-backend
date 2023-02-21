package oliin.apps.workplacer.sabre.rest.mapper;

import oliin.apps.workplacer.auth.domain.model.UserRole;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Mapper(componentModel = "spring")
public abstract class RoleMapper {

//    @Value(UserRole.OFFICE_MANAGER)
//    protected UserRole merchant;
//
//    public String getKeyCloakRole(UserRole role) {
//        return merchant;
//    }

}
