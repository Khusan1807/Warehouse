package uz.pdp.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.entity.temlate.AbsNameIdActive;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Client extends AbsNameIdActive {

    @Column(unique = true, nullable = false)
    private String phoneNumber;

}
