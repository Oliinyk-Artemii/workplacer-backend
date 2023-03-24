package oliin.apps.workplacer.domain.feature.company;

import lombok.RequiredArgsConstructor;
import oliin.apps.workplacer.domain.feature.company.model.Company;
import oliin.apps.workplacer.domain.feature.company.repository.CreateCompanyRepository;
import oliin.apps.workplacer.domain.feature.user.model.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCompany {
    private final CreateCompanyRepository createCompanyRepository;

    public String doCreateCompany(String companyName) {
        final Company company = createCompanyRepository.createCompany(companyName);
        return company.getId();
    }
}

