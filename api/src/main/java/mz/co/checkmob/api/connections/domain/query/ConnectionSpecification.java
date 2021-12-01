package mz.co.checkmob.api.connections.domain.query;

import lombok.RequiredArgsConstructor;
import mz.co.checkmob.api.connections.domain.Connection;
import mz.co.checkmob.api.connections.domain.query.ConnectionQuery;
import mz.co.checkmob.api.connections.persistence.ConnectionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ConnectionSpecification {
    private final ConnectionRepository connectionRepository;

    public Page<Connection> executeQuery(ConnectionQuery connectionQuery, Pageable pageable){
        return connectionRepository.findAll (toSpecification(connectionQuery),pageable);
    }

    public Specification<Connection> toSpecification (ConnectionQuery connectionQuery){
        return all()
                .and(findByName(connectionQuery.getName()));
    }

    public static Specification<Connection> all(){
        return  ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and());
    }

    public static Specification<Connection>findByName(String name){
        if(name==null) return  null;
        return (Specification<Connection>)(root, criteriaQuery, criteriaBuilder)->{
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),"%"+name.toLowerCase()+"%");
        };
    }

}
