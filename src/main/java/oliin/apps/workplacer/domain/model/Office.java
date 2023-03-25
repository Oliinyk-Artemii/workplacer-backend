package oliin.apps.workplacer.domain.model;

import jakarta.persistence.*;
import lombok.*;
import oliin.apps.workplacer.domain.model.user.User;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "offices")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Office {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "office_id")
    @Getter
    private String id;
    @Getter
    private String name;
    @Column(name = "active")
    private boolean isActive;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_offices",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "office_id")}
    )
    private Set<User> users = new HashSet<>();

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "company_offices",
            joinColumns = {@JoinColumn(name = "office_id")},
            inverseJoinColumns = {@JoinColumn(name = "company_id")}
    )
    private Company company;

    public void addUser(User user) {
        if (users == null) {
            users = new HashSet<>();
            users.add(user);
        } else {
            users.add(user);
        }
        user.getOffices().add(this);
    }

    public void setCompany(Company company) {
        this.company = company;
        company.setOffice(this);
    }
}
