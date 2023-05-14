package oliin.apps.workplacer.feature.user.domain;

import oliin.apps.workplacer.config.JwtService;
import oliin.apps.workplacer.domain.model.user.CreateUserRequest;
import oliin.apps.workplacer.domain.model.user.User;
import oliin.apps.workplacer.domain.repository.CreateUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegisterServiceTest {

    @InjectMocks
    private RegisterService registerService;
    @Mock
    private CreateUserRepository createUserRepository;
    @Mock
    private JwtService jwtService;

    @Test
    void doSignUp() {
        final CreateUserRequest fakeCreateUserRequest = CreateUserRequest
                .builder()
                .email("email")
                .build();
        final User fakeUser = User
                .builder()
                .id("id")
                .email("email")
                .build();

        when(createUserRepository.createUser(fakeCreateUserRequest)).thenReturn(fakeUser);
        when(jwtService.generateToken(fakeUser)).thenReturn("access-token");

        String token = registerService.doSignUp(fakeCreateUserRequest);

        assertEquals("access-token", token);
        verify(createUserRepository).createUser(fakeCreateUserRequest);
    }
}
