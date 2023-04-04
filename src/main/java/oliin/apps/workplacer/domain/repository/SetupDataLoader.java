package oliin.apps.workplacer.domain.repository;

import lombok.RequiredArgsConstructor;
import oliin.apps.workplacer.domain.model.Company;
import oliin.apps.workplacer.domain.model.Office;
import oliin.apps.workplacer.domain.model.user.User;
import oliin.apps.workplacer.feature.user.domain.model.AuthorityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    private OfficeRepository officeRepository;

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

        Office office = Office
                .builder()
                .name("Office name")
                .isActive(true)
                .build();

        office.setCompany(company);

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
        office.addUser(officeManger);
        office.addUser(employee);

        companyRepository.save(company);
        officeRepository.save(office);

        alreadySetup = true;
    }
}
