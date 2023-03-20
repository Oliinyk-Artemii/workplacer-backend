package oliin.apps.workplacer.domain.model;


import jakarta.persistence.*;
import lombok.*;
import oliin.apps.workplacer.rest.model.AuthorityType;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
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
    @Column(name = "company-ids")
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
