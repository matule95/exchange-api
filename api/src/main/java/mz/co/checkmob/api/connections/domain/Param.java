package mz.co.checkmob.api.connections.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import mz.co.checkmob.api.core.utils.AttachmentsConverter;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "params")
public class Param {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Convert(converter = AttachmentsConverter.class)
    private String [] fromField;

    @Convert(converter = AttachmentsConverter.class)
    private String [] toField;

    @ManyToOne
    @JsonIgnore
    private Connection connection;

    @Enumerated(EnumType.STRING)
    @Column(name = "operation_type")
    private OperationType operationType;


    private String delimiter;

}
