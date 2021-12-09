package mz.co.exchange.api.company.domain.query;

import lombok.RequiredArgsConstructor;
import mz.co.exchange.api.company.domain.Company;
import mz.co.exchange.api.company.domain.CompanyMapper;
import mz.co.exchange.api.company.persistence.CompanyRepository;
import mz.co.exchange.api.company.presentation.CompanyJson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyQueryGateway {
    private final CompanyRepository repository;
    public Page<CompanyJson> executeQuery(Pageable pageable, CompanyQuery companyQuery){
        return CompanyMapper.INSTANCE.mapToJson(repository.findAll(toSpecification(companyQuery), pageable));
    }

    public Specification<Company> toSpecification(CompanyQuery companyQuery){
        return all()
                .and(findByName(companyQuery.getName()));
    }

    public static Specification<Company> all(){
        return (root, criteriaQuery, criteriaBuilder)-> criteriaBuilder.and();
    }

    public static Specification<Company> findByName(String name) {
        if (name == null) return null;
        return (Specification<Company>) (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        };
    }



}
