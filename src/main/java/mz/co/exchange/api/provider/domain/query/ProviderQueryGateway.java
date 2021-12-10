package mz.co.exchange.api.provider.domain.query;

import lombok.RequiredArgsConstructor;
import mz.co.exchange.api.provider.domain.Provider;
import mz.co.exchange.api.provider.domain.ProviderMapper;
import mz.co.exchange.api.provider.persistence.ProviderRepository;
import mz.co.exchange.api.provider.presentation.ProviderJson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProviderQueryGateway {
    private final ProviderRepository repository;
    public Page<ProviderJson> executeQuery(Pageable pageable, ProviderQuery providerQuery){
        return ProviderMapper.INSTANCE.mapToJson(repository.findAll(toSpecification(providerQuery), pageable));
    }

    public Specification<Provider> toSpecification(ProviderQuery providerQuery){
        return all()
                .and(findByName(providerQuery.getName()));
    }

    public static Specification<Provider> all(){
        return (root, criteriaQuery, criteriaBuilder)-> criteriaBuilder.and();
    }

    public static Specification<Provider> findByName(String name) {
        if (name == null) return null;
        return (Specification<Provider>) (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        };
    }



}
