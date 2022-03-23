package uz.pdp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import uz.pdp.entity.temlate.AbsId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Input extends AbsId {

    @CreationTimestamp
    private Timestamp date;

    @ManyToOne // Ko'plab kirimlar bitta ombor
    private Warehouse warehouse;

    @ManyToOne // Ko'plab kiriblarni bitta taminotchi qilishi mumkin:
    private Supplier supplier;

    @ManyToOne // Ko'plab kirimlar bitta valyutada:
    private Currency currency;

    private String factureNumber;

    @Column(unique = true, nullable = false)
    private String code;

}
