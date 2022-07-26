package th.ac.ku.menu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.menu.model.Menu;
import th.ac.ku.menu.service.MenuService;

import java.util.List;
import java.util.UUID;

@RestController //เป็นcontrollerที่ทำREST Apiให้
@RequestMapping("/menu")//เป็นbase url คือถ้าใส่เป็น ku.th/menu ก็จะมาในcontrollerนี้ให้(เหมือนเป็นdeafault)
public class MenuController {
    @Autowired //ไปที่serviceเนื่องจากcontrollerเชื่อมกับservice
    private MenuService service;

    @GetMapping //ถ้าเรีนกใช้ด้วยget functionจะมาที่methodนี้อัตโนมัติ
    public List<Menu> getAll() {
        return service.getAll(); //คืนค่าเป็นlistของเมนูทุกอัน เรียกmethod getAll()ของservice
    }

    @PostMapping//ถ้าอยากinsertใช้เป็นPost
    public Menu create(@RequestBody Menu menu) {//ส่งobjectเมนูเข้ามาผ่าน@RequestBody
        return service.create(menu);//มาเรียนmethod createของservice
    }

    @GetMapping("/{id}")//ถ้าเรีนกใช้ด้วยget functionแล้วมีpath variableเป็นidจะมาที่อันนี้
    public Menu getMenuById(@PathVariable UUID id) {
        return service.getMenuById(id);
    }

    @PutMapping//ถ้าเรีนกใช้ด้วยput functionจะมาที่methodนี้อัตโนมัติ
    public Menu update(@RequestBody Menu menu) {
        return service.update(menu);
    }

    @DeleteMapping("/{id}")//ถ้าเรีนกใช้ด้วยdelete functionจะมาที่methodนี้อัตโนมัติ
    public Menu delete(@PathVariable UUID id) {
        return service.delete(id);
    }

    @GetMapping("/name/{name}")
    public Menu getMenuByName(@PathVariable String name) {
        return service.getMenuByName(name);
    }

    @GetMapping("/category/{category}")
    public List<Menu> getMenuByCategory(@PathVariable String category) {
        return service.getMenuByCategory(category);
    }



}
