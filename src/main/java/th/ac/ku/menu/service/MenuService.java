package th.ac.ku.menu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.ac.ku.menu.model.Menu;
import th.ac.ku.menu.repository.MenuRepository;

import java.util.List;
import java.util.UUID;

@Service //เหมือน@component Spring จะสร้าง object นี้ให้อัตโนมัติ และมีลักษณะเป็น service ที่จะทำงานตลอดเวลาเพื่อให้บริการ
public class MenuService {
    @Autowired //ให้spring framwork สร้างobject repositoryแล้วส่งผ่านมาทางพารามิเตอร์ของ constructor หรือ setter อัตโนมัติ
    private MenuRepository menuRepository;//ในserviceต้องเชื่อมต่อกับ repository เลยต้องมีMenuRepositoryอยู่ในนั้น

    public List<Menu> getAll() {//getAll() เหมือน select all คืนค่าเป็นlistของเมนู
        return menuRepository.findAll();//การใช้งานคือเรียก menuRepository.findAll()
    }//menuRepository เป็น interface ซึ่งมีนั้นextend jpa ที่มีพวกคำสั่งselect create updateอยูู่

    public Menu create(Menu menu) {//create()เหมือนinsert -> Menu menu รับเป็นobjectเลย
        Menu record = menuRepository.save(menu);//save()จะคืนค่าเป็นobjectที่generate id ให้แล้ว
        return record;//เลยเอาrecordมาแสดงผลได้เลย
    }

    public Menu getMenuById(UUID id) { //เพิ่มการGETด้วยid
        return menuRepository.findById(id).get();
    }//เหมือนหาid select where(id) = id

    public Menu update(Menu requestBody) { //เพิ่มการPUT
        UUID id = requestBody.getId();
        Menu record = menuRepository.findById(id).get();
        record.setName(requestBody.getName());
        record.setPrice(requestBody.getPrice());
        record.setCategory(requestBody.getCategory());

        record = menuRepository.save(record);
        return record;
    }//เพิ่มการupdate แบบมีการเปลี่ยน แก้ไขข้อมูลแล้วจะมีupdate

    public Menu delete(UUID id) {
        Menu record = menuRepository.findById(id).get();
        menuRepository.deleteById(id);
        return record;
    }

    public Menu getMenuByName(String name) { //ค้นหาด้วยname
        return menuRepository.findByName(name);
    }

    public List<Menu> getMenuByCategory(String category) { //ค้นหาด้วยcategory
        return menuRepository.findByCategory(category);
    }



}
