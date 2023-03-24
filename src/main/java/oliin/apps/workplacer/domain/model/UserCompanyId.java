package oliin.apps.workplacer.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserCompanyId implements Serializable {

    @Column(name = "user_id")
    private String userId;

    @Column(name = "company_id")
    private String companyId;
}