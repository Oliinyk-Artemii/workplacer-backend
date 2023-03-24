package oliin.apps.workplacer.domain.feature.user;

import lombok.AllArgsConstructor;
import oliin.apps.workplacer.domain.feature.user.model.User;
import oliin.apps.workplacer.domain.feature.user.model.UserDetailsImpl;
import oliin.apps.workplacer.domain.feature.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(username);

        return userOptional.map(UserDetailsImpl::new).orElseThrow(() -> new UsernameNotFoundException("Could not find user"));
    }

}
