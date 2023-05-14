package oliin.apps.workplacer.domain.repository;

import oliin.apps.workplacer.domain.model.Company;
import oliin.apps.workplacer.domain.model.Office;
import oliin.apps.workplacer.domain.model.user.CreateUserRequest;
import oliin.apps.workplacer.domain.model.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateRepositoryTest {

    @InjectMocks
    private CreateUserRepository createUserRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private OfficeRepository officeRepository;

    @Test
    void doSignUp() {
        final CreateUserRequest fakeCreateUserRequest = CreateUserRequest
                .builder()
                .email("email")
                .password("password")
                .companyIds(Set.of("id"))
                .officeIds(Set.of("id"))
                .build();
        final Company fakeCompany = Company
                .builder()
                .id("id")
                .name("company")
                .build();
        final Office fakeOffice = Office
                .builder()
                .id("id")
                .name("office")
                .build();
        final User fakeUser = User
                .builder()
                .email("email")
                .password(passwordEncoder.encode(fakeCreateUserRequest.getPassword()))
                .companies(new HashSet<>())
                .offices(new HashSet<>())
                .build();
        fakeOffice.addUser(fakeUser);
        fakeCompany.addUser(fakeUser);

        when(userRepository.findByEmail(fakeCreateUserRequest.getEmail())).thenReturn(Optional.empty());
        when(companyRepository.findCompaniesByIdIn(fakeCreateUserRequest.getCompanyIds())).thenReturn(Set.of(fakeCompany));
        when(officeRepository.findOfficesByIdIn(fakeCreateUserRequest.getOfficeIds())).thenReturn(Set.of(fakeOffice));
        when(userRepository.save(fakeUser)).thenAnswer(I -> {
            fakeUser.setId("id");
            return fakeUser;
        });

        User user = createUserRepository.createUser(fakeCreateUserRequest);

        assertEquals(fakeUser, user);
    }
}