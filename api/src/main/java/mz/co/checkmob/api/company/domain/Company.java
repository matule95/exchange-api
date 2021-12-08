package mz.co.checkmob.api.company.domain;

import lombok.Data;
import mz.co.checkmob.api.endpoint.domain.Endpoint;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static mz.co.checkmob.api.company.domain.CompanyStatus.ACTIVE;

@Entity
@Table(name = "companies")
@SQLDelete(sql = "UPDATE companies SET deleted_at = now() WHERE id=?")
@Where(clause = "deleted_at is null")
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String path;
    private String baseUrl;

    private CompanyStatus companyStatus=ACTIVE;

    @Column(name = "username_checkmob")
    private String usernameCheckmob;
    @Column(name = "password_checkmob")
    private String passwordCheckmob;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "company", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Endpoint> endpoints = new ArrayList<>();

}
