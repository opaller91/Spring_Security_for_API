package th.ac.ku.menu.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;
//classนี้แค่สร้างตารางพวกqueryจะไปอีกclassนึง ตรงpackage repository
@Entity //เพื่อทำเหมือนORMต่อdbอัตโนมัติ บอกว่าเป็นตารางให้create tableให้โดยมีข้อมูลในตารางตามนี้ พวกattibuteอย่าง id name เหมือนเป็นfield
//spring framwork จะไปสร้างobjectให้ซึ่งobjectนั้นจะถูกสร้างจากฐานข้อมูลloadมา
public class Menu { //เหมือนเป็นdataclass พวกaccount,customerที่ถูกเก็บไว้ในไฟล์
    @Id //บอกว่าอันนี้คือprimary key มันจะไปsetเป็นpkให้ตอนcreate table
    @GeneratedValue //สามารถระบุได้ว่าให้dbนั้น auto gennerate id ให้แบบramdom(ปลอดภัยกว่า?) แต่ถ้าระบุเป็ยintมันจะเรียงลงมา12345
    private UUID id; //UUID ใช้กับpk -> universal uniq id (256bit)
    private String name;
    private double price;//ราคาเมนู
    private String category;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
