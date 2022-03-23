package uz.pdp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.entity.temlate.AbsNameIdActive;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category extends AbsNameIdActive {

    // Misol uchun:
    // id ==>  1, 10, 20, 25
    // name ==> Elektronika, Telefeon, Audio-Video Tech, TVs

    @ManyToOne // Ko'plab bo'la kategoriyalarni bitta otasi bo'ladi, yoki otasi bo'lmashligi ham mumkin:
    private Category parentCategoryId; // null, 1, 1, 20

    // active = true, true, true, true

}
