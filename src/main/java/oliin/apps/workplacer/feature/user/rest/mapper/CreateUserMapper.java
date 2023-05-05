package oliin.apps.workplacer.feature.user.rest.mapper;

import oliin.apps.workplacer.domain.model.user.CreateUserRequest;
import oliin.apps.workplacer.feature.user.domain.model.AuthorityType;
import oliin.apps.workplacer.feature.user.rest.CreateUserController;
import oliin.apps.workplacer.feature.user.rest.RegisterController;
import org.mapstruct.*;

import java.util.Collections;
import java.util.Set;


@MapperConfig(unmappedTargetPolicy = ReportingPolicy.ERROR)
@Mapper(componentModel = "spring", imports = {Collections.class, AuthorityType.class, Set.class})
public interface CreateUserMapper {
    @Mapping(target = "companyIds", expression = "java(Collections.<String>emptySet())")
    @Mapping(target = "officeIds", expression = "java(Collections.<String>emptySet())")
    @Mapping(target = "authorities", expression = "java(Set.of(AuthorityType.OFFICE_MANAGER))")
    CreateUserRequest toCreateUserRequest(RegisterController.RegisterRequest request);

    @Mapping(source = "companyId", target = "companyIds", qualifiedByName = "idToIdsSet")
    @Mapping(source = "officeId", target = "officeIds", qualifiedByName = "idToIdsSet")
    @Mapping(source = "authorityType", target = "authorities", qualifiedByName = "authorityToSet")
    CreateUserRequest toCreateUserRequest(CreateUserController.CreateUserRequest request);

    @Named("authorityToSet")
    public static Set<AuthorityType> authorityToSet(AuthorityType authorityType) {
        return Set.of(authorityType);
    }

    @Named("idToIdsSet")
    public static Set idToIdsSet(String id) {
        return Set.of(id);
    }
}
