package uz.pdp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.entity.attachment.Attachment;
import uz.pdp.entity.temlate.AbsNameIdActive;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends AbsNameIdActive {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String code;

    @ManyToOne(optional = false) // not null ga teng
    private Category category;

    @OneToOne
    private Attachment photo;

    @ManyToOne(optional = false)
    // KO'p mahsulotlarga bitta o'lchov birligi, shakar, g'shi, olma ga ==> kiloda o'lchanadi:
    private Measurement measurement;

    public Product(Category category, Attachment photo, Measurement measurement) {
        this.category = category;
        this.photo = photo;
        this.measurement = measurement;
    }
}
