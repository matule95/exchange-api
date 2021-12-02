package mz.co.checkmob.api.endpoint.domain.query;

import lombok.RequiredArgsConstructor;

import mz.co.checkmob.api.endpoint.domain.Endpoint;
import mz.co.checkmob.api.endpoint.persistence.EndpointRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EndpointSpecification {
    private final EndpointRepository endpointRepository;
    public Page<Endpoint>executeQuery(EndpointQuery endpointQuery, Pageable pageable){
        return endpointRepository.findAll(toSpecification(endpointQuery),pageable);
    }

    public Specification<Endpoint> toSpecification (EndpointQuery endpointQuery){
        return all()
                .and(findByName(endpointQuery.getName()));
    }

    public static Specification<Endpoint> all(){
        return  ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and());
    }

    public static Specification<Endpoint>findByName(String name){
        if(name==null) return  null;
        return (Specification<Endpoint>)(root, criteriaQuery, criteriaBuilder)->{
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),"%"+name.toLowerCase()+"%");
        };
    }

}
