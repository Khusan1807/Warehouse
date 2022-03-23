package uz.pdp.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.entity.temlate.AbsNameIdActive;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Currency extends AbsNameIdActive {


}
