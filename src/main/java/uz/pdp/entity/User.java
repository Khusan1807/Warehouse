package uz.pdp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.entity.temlate.AbsId;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User extends AbsId {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String password;

    private boolean active=true; // userni registratsiya qilganimizda aktiv bo'lib qo'lishi uchun:


    @ManyToMany // bitta user bir nechta omborda ishlashi mumkin, bitta omborda esa ko'pp user ishlashi mumkin.
    private Set<Warehouse> warehouses; // Set qo'yilganiga sabab bitta userga bitta ombor biriktirilgandan so'ng
    // o'sha user yana o'sha idilik omborga qo'shilmasligi.


    public User(String firstName, String lastName, String phoneNumber, String code, String password, Set<Warehouse> warehouses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.code = code;
        this.password = password;
        this.warehouses = warehouses;
    }
}
