package oliin.apps.workplacer.feature.company.rest.mapper;

import oliin.apps.workplacer.domain.model.Company;
import oliin.apps.workplacer.feature.company.rest.CompanyController;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@MapperConfig(unmappedTargetPolicy = ReportingPolicy.ERROR)
@Mapper(componentModel = "spring")
public interface CompanyResponseMapper {

    @Mapping(source = "active", target = "isActive")
    CompanyController.CompanyResponse toCompanyResponse(Company company);
}
