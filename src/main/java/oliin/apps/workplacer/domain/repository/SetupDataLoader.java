package oliin.apps.workplacer.domain.repository;

import lombok.RequiredArgsConstructor;
import oliin.apps.workplacer.domain.feature.company.model.Company;
import oliin.apps.workplacer.domain.feature.company.repository.CompanyRepository;
import oliin.apps.workplacer.domain.feature.user.model.User;
import oliin.apps.workplacer.domain.feature.user.repository.UserRepository;
import oliin.apps.workplacer.rest.feature.user.model.AuthorityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
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
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) return;

        Company company = Company.builder()
                .name("Company name")
                .isActive(true)
                .build();

        User officeManger = new User();
        officeManger.setFirstName("Office");
        officeManger.setLastName("Manager");
        officeManger.setPassword(passwordEncoder.encode("test"));
        officeManger.setEmail("manager");
        officeManger.setRoles(Set.of(AuthorityType.OFFICE_MANAGER));
        officeManger.setCompanies(new HashSet<>());

        User employee = new User();
        employee.setFirstName("Employee");
        employee.setLastName("LoooongLastName");
        employee.setPassword(passwordEncoder.encode("test"));
        employee.setEmail("employee");
        employee.setRoles(Set.of(AuthorityType.EMPLOYEE));
        employee.setCompanies(new HashSet<>());

        company.addUser(officeManger);
        company.addUser(employee);

        companyRepository.save(company);

        alreadySetup = true;
    }
}
