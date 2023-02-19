package oliin.apps.workplacer.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.domain.exception.UserExistsException;
import oliin.apps.workplacer.domain.model.UserModel;
import oliin.apps.workplacer.domain.model.UserRole;
import oliin.apps.workplacer.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserRegistry {
    //    private final UsersResourceAccessor usersResourceAccessor;
//    private final UserRoleAccessor userRoleAccessor;
//    private final UserRepresentationMapper userRepresentationMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

//    public Optional<UserRepresentation> getUser(String msisdn) {
//        UsersResource usersResource = usersResourceAccessor.getUsers();
//
//        List<UserRepresentation> userRepresentations = usersResource.search(msisdn, true);
//        return userRepresentations.isEmpty() ? Optional.empty() : Optional.of(userRepresentations.get(0));
//    }

//    public boolean userExist(String msisdn) {
//        return !usersResourceAccessor.getUsers().search(msisdn, true).isEmpty();
//    }

    public UserModel createUser(String email, String password, UserRole role, String firstName, String lastName) {
        if (userRepository.findByEmail(email).isPresent()) {
            log.error("The user with email {} is already registered", email);
            throw new UserExistsException("User already exists");
        }

        var user = UserModel.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .userType(role)
                .firstName(firstName)
                .lastName(lastName)
                .companies(new LinkedList<>()) // TODO add companies
                .offices(new LinkedList<>()) // TODO add offices
                .userType(role)
                .build();
//        UsersResource usersResource = usersResourceAccessor.getUsers();

        userRepository.save(user);
        log.info("Created the user {}", email);
        return user;
//        HttpStatus result = HttpStatus.valueOf(response.getStatus());
//        if (result.is2xxSuccessful()) {
//            addRole(CreatedResponseUtil.getCreatedId(response), role);
//        } else {
//            if (result == HttpStatus.CONFLICT) {
//                log.error("The user with msisdn {} is already registered in Keycloak", email);
//                throw new UserExistsException("Keycloak already exist user");
//            }
//            log.error("User with msisdn {} cannot be created: {}", email, response.getStatusInfo());
//            throw new IntegrationException("Keycloak failed to create a user " + email);
//        }

    }

//    public void addDeviceToAttribute(String msisdn, String attributeValue) {
//        UsersResource users = usersResourceAccessor.getUsers();
//        UserRepresentation userRepresentation = users.search(msisdn, true).get(0);
//        List<String> listAttributes = userRepresentationMapper.listDevices(userRepresentation.getAttributes());
//        if (listAttributes.contains(attributeValue)) {
//            log.warn("Attribute device-id: {} - has already been added to the trusted list", attributeValue);
//        } else {
//            listAttributes.add(attributeValue);
//            users.get(userRepresentation.getId()).update(userRepresentation);
//        }
//    }

//    public void removeDeviceFromAttribute(String msisdn, String attributeValue) {
//        UsersResource users = usersResourceAccessor.getUsers();
//        UserRepresentation userRepresentation = users.search(msisdn, true).get(0);
//        List<String> listAttributes = userRepresentationMapper.listDevices(userRepresentation.getAttributes());
//
//        boolean attributeRemoved = listAttributes.remove(attributeValue);
//        if (attributeRemoved) {
//            users.get(userRepresentation.getId()).update(userRepresentation);
//        } else {
//            log.warn("Attribute device-id: {} - was not present in the trusted list", attributeValue);
//        }
//    }
//
//    public void addRole(String userId, String role) {
//        log.debug("Add role {} to user - {}", role, userId);
//        UserResource userResource = usersResourceAccessor.getUsers().get(userId);
//        RoleRepresentation roleRepresentation = userRoleAccessor.getRole(role);
//        List<RoleRepresentation> roleRepresentations = userResource.roles().realmLevel().listAll();
//        roleRepresentations.add(roleRepresentation);
//        userResource.roles().realmLevel().add(roleRepresentations);
//    }
}
