package oliin.apps.workplacer.domain.feature.user.model;


import jakarta.persistence.*;
import lombok.*;
import oliin.apps.workplacer.rest.feature.user.model.AuthorityType;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String firstName;
    private String lastName;
    @Column(name = "email", unique = true)
    private String email;
    private String password;
    @ElementCollection
    @Column(name = "Company-ids")
    private Set<String> companyIds;
    @ElementCollection
    @Column(name = "office-ids")
    private Set<String> officeIds;
//    @Enumerated(EnumType.STRING)
//    private UserRoles userType;

    @ElementCollection(targetClass = AuthorityType.class)
    @JoinTable(name = "user_authorities", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "authority", nullable = false)
    private Set<AuthorityType> roles;

}
