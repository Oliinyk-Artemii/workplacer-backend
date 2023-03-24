package oliin.apps.workplacer.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import oliin.apps.workplacer.domain.feature.company.model.Company;
import oliin.apps.workplacer.domain.feature.user.model.User;

import java.util.Date;

@Data
@Entity
@Table(name = "user_company")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCompany {
    @EmbeddedId
    private UserCompanyId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("companyId")
    private Company company;

    @Column(name = "created_on")
    private Date createdOn = new Date();

    public UserCompany(User user, Company company) {
        this.user = user;
        this.company = company;
        this.id = new UserCompanyId(user.getId(), company.getId());
    }
}
