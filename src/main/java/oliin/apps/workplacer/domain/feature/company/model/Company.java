package oliin.apps.workplacer.domain.feature.company.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import oliin.apps.workplacer.domain.feature.user.model.User;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;

@Data
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
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "active")
    private boolean isActive;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_companies",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "company_id")}
    )
    private Set<User> users = new HashSet<>();

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
