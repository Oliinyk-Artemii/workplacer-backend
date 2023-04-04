package oliin.apps.workplacer.feature.office.rest.mapper;

import oliin.apps.workplacer.domain.model.Company;
import oliin.apps.workplacer.domain.model.Office;
import oliin.apps.workplacer.feature.office.rest.OfficeController;
import org.mapstruct.*;

@MapperConfig(unmappedTargetPolicy = ReportingPolicy.ERROR)
@Mapper(componentModel = "spring")
public interface OfficeResponseMapper {

    @Mapping(source = "active", target = "isActive")
    @Mapping(source = "company", target = "companyId", qualifiedByName = "companyToCompanyId")
    OfficeController.OfficeResponse toOfficeResponse(Office office);

    @Named("companyToCompanyId")
    public static String companyToCompanyId(Company company) {
        return company.getId();
    }
}