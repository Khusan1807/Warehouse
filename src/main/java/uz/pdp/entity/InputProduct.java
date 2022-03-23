package uz.pdp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.entity.temlate.AbsId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InputProduct extends AbsId {

    @ManyToOne
    // ko'plab kirim mahsulotlarida bitta mahsi=ulot kirishi mumkin, A kirindayam Iphone, B kirimdayam Iphone 13
    private Product product;

    @Column(nullable = false)
    private Double amount; // o'sha product dan nechta kelganligi

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Date expireDate;

    @ManyToOne
    private Input input;

}

