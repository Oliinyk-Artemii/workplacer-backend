package oliin.apps.workplacer.domain.repository;

import lombok.RequiredArgsConstructor;
import oliin.apps.workplacer.domain.model.User;
import oliin.apps.workplacer.rest.model.AuthorityType;
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
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        User officeManger = new User();
        officeManger.setFirstName("Office");
        officeManger.setLastName("Manager");
        officeManger.setPassword(passwordEncoder.encode("test"));
        officeManger.setEmail("test");
        officeManger.setRoles(Set.of(AuthorityType.OFFICE_MANAGER));
        userRepository.save(officeManger);

        User employee = new User();
        employee.setFirstName("Employee");
        employee.setLastName("LoooongLastName");
        employee.setPassword(passwordEncoder.encode("test"));
        employee.setEmail("employee");
        employee.setRoles(Set.of(AuthorityType.EMPLOYEE));
        userRepository.save(employee);

        alreadySetup = true;
    }
}
