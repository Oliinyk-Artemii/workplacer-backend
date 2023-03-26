package oliin.apps.workplacer.domain.model;

import jakarta.persistence.*;
import lombok.*;
import oliin.apps.workplacer.domain.model.user.User;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "companies")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "company_id")
    @Getter
    private String id;
    @Column(name = "name")
    @Getter
    private String name;
    @Column(name = "active")
    private boolean isActive;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_companies",
            joinColumns = {@JoinColumn(name = "company_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private Set<User> users = new HashSet<>();

    @OneToOne(mappedBy = "company", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @Setter
    private Office office;

    public void addUser(User user) {
        if (users == null) {
            users = new HashSet<>();
            users.add(user);
        } else {
            users.add(user);
        }
        user.getCompanies().add(this);
    }
}
