package oliin.apps.workplacer.domain.feature.user.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import oliin.apps.workplacer.domain.feature.company.model.Company;
import oliin.apps.workplacer.rest.feature.user.model.AuthorityType;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
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
    @Column(name = "user_id")
    private String id;
    private String firstName;
    private String lastName;
    @Column(name = "email", unique = true)
    private String email;
    private String password;
    @JsonIgnore
    @ManyToMany(mappedBy = "users", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @Column(name = "companies", nullable = false)
    private Set<Company> companies = new HashSet<>();
    @ElementCollection
    @Column(name = "office-ids", nullable = false)
    private Set<String> officeIds = new HashSet<>();
//    @Enumerated(EnumType.STRING)
//    private UserRoles userType;

    @ElementCollection(targetClass = AuthorityType.class)
    @JoinTable(name = "user_authorities", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "authority", nullable = false)
    private Set<AuthorityType> roles;
}
