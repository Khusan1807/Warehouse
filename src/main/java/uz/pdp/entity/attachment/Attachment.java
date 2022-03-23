package uz.pdp.entity.attachment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.entity.temlate.AbsNameId;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Attachment extends AbsNameId {


    @Column(nullable = false)
    private long size;

    @Column(nullable = false)
    private String ContentType;

}
