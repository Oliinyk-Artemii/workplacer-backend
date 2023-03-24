package oliin.apps.workplacer.domain.repository;

import lombok.RequiredArgsConstructor;
import oliin.apps.workplacer.domain.feature.company.model.Company;
import oliin.apps.workplacer.domain.feature.company.repository.CompanyRepository;
import oliin.apps.workplacer.domain.feature.user.model.User;
import oliin.apps.workplacer.domain.feature.user.repository.UserRepository;
import oliin.apps.workplacer.domain.model.UserCompany;
import oliin.apps.workplacer.domain.model.UserCompanyId;
import oliin.apps.workplacer.rest.feature.user.model.AuthorityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private UserCompanyRepository userCompanyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Company company = Company.builder()
                .name("Company name")
                .isActive(true)
                .build();
        companyRepository.save(company);

        User officeManger = new User();
        officeManger.setFirstName("Office");
        officeManger.setLastName("Manager");
        officeManger.setPassword(passwordEncoder.encode("test"));
        officeManger.setEmail("manager");
        officeManger.setRoles(Set.of(AuthorityType.OFFICE_MANAGER));
        officeManger.setCompanies(Collections.emptySet());
        officeManger.addCompany(company);
        userRepository.save(officeManger);

        User employee = new User();
        employee.setFirstName("Employee");
        employee.setLastName("LoooongLastName");
        employee.setPassword(passwordEncoder.encode("test"));
        employee.setEmail("employee");
        employee.setRoles(Set.of(AuthorityType.EMPLOYEE));
        officeManger.setCompanies(Collections.emptySet());
        employee.addCompany(company);
        userRepository.save(employee);

        UserCompany userCompanyEmployee = UserCompany.builder()
                .user(employee)
                .company(company)
                .id(new UserCompanyId(employee.getId(), company.getId()))
                .build();
        userCompanyRepository.save(userCompanyEmployee);

        UserCompany userCompanyOfficeManager = UserCompany.builder()
                .user(officeManger)
                .company(company)
                .id(new UserCompanyId(officeManger.getId(), company.getId()))
                .build();
        userCompanyRepository.save(userCompanyOfficeManager);

        alreadySetup = true;
    }
}
