package oliin.apps.workplacer.feature.user.domain;

import oliin.apps.workplacer.config.JwtService;
import oliin.apps.workplacer.domain.model.user.User;
import oliin.apps.workplacer.domain.model.user.UserCredentials;
import oliin.apps.workplacer.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SignInTest {

    @InjectMocks
    private SignInService signInService;
    @Mock
    private JwtService jwtService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private AuthenticationManager authenticationManager;

    @Test
    void doSignIn() {
        final User fakeUser = User
                .builder()
                .id("id")
                .email("email")
                .build();

        when(userRepository.findByEmail("email")).thenReturn(Optional.of(fakeUser));

        User user = signInService.doSignIn(new UserCredentials("email", "password"));

        assertEquals(fakeUser, user);
        verify(authenticationManager).authenticate(new UsernamePasswordAuthenticationToken(
                "email",
                "password"
        ));
    }

    @Test
    void generateUserAccessToken() {
        final User fakeUser = User
                .builder()
                .id("id")
                .email("email")
                .build();

        when(jwtService.generateToken(fakeUser)).thenReturn("access-token");

        String accessToken = signInService.generateUserAccessToken(fakeUser);

        assertEquals(accessToken, "access-token");
    }
}
