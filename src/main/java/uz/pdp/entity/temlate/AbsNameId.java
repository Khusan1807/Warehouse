package uz.pdp.entity.temlate;

import lombok.Data;

import javax.persistence.*;

@Data
@MappedSuperclass
public abstract class AbsNameId {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
}
