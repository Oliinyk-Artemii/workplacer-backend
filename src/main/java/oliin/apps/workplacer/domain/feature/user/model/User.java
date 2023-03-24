package oliin.apps.workplacer.domain.feature.user.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import oliin.apps.workplacer.domain.feature.company.model.Company;
import oliin.apps.workplacer.domain.model.UserCompany;
import oliin.apps.workplacer.domain.model.UserCompanyId;
import oliin.apps.workplacer.rest.feature.user.model.AuthorityType;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Collections;
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
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<UserCompany> companies = Collections.emptySet();
    @ElementCollection
    @Column(name = "office-ids")
    private Set<String> officeIds = Collections.emptySet();
//    @Enumerated(EnumType.STRING)
//    private UserRoles userType;

    @ElementCollection(targetClass = AuthorityType.class)
    @JoinTable(name = "user_authorities", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "authority", nullable = false)
    private Set<AuthorityType> roles;

    public void addCompany(Company company) {
        UserCompany userCompany = new UserCompany(this, company);
//        if (companies == null) {
//            companies = Set.of(userCompany);
//        } else {
//            companies.add(userCompany);
//        }
        companies = Set.of(userCompany);
    }

}
