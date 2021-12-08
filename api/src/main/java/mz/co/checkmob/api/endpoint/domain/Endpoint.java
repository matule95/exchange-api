package mz.co.checkmob.api.endpoint.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import mz.co.checkmob.api.authorization.domain.AuthorizationType;
import mz.co.checkmob.api.company.domain.Company;
import mz.co.checkmob.api.utils.JsonObjectConverter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Getter
@Setter
@ToString
@Table(name = "endpoints")
@SQLDelete(sql = "UPDATE endpoints SET deleted_at = now() WHERE id=?")
@Where(clause = "deleted_at is null")
@AllArgsConstructor
@NoArgsConstructor
public class Endpoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String url;

    private AuthorizationType authenticationType;

    @JsonIgnore
    @ManyToOne
    private Company company;

    @Convert(converter = JsonObjectConverter.class)
    private Map<String, Object> dataReader;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    public Map<String,Object> authenticate(){
        String auth = "authURL";
        String prefix = "prefix";
        String responseKey = "response";
        String responseValue = dataReader.get("response").toString();
        String headerValue = "header";
        Object authenticationURL = this.dataReader.getOrDefault(auth,"");
        this.dataReader.remove(auth);
        this.dataReader.remove(prefix);
        this.dataReader.remove(responseKey);
        this.dataReader.remove(responseValue);
        this.dataReader.remove(headerValue);
        Map<String,Object> map= this.authenticationType.authentication(String.valueOf(authenticationURL),dataReader);
        return map;
    }
}
