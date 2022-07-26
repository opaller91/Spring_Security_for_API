package th.ac.ku.menu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.ac.ku.menu.model.Menu;

import java.util.List;
import java.util.UUID;

@Repository //เป็นkeyชนิดนึงที่ทำหน้าที่เป็นrepositoryเชื่อมdb ดูคำอธิบายในdoc ในdrive
//entity เหมือนเป็นแถวหนึ่งแถว repositoryเหมือนเป็นclassนึงที่ข้างในมี method พวก select all insert update delete โดยauto jpaทำให้
public interface MenuRepository extends JpaRepository<Menu, UUID> {//ดูคำอธิบายในdoc ในdrive
                                              // Menu->เพราะdataของเราเป็นMenu UUIDเพราะpkมีtypeเป็นUUI
    Menu findByName(String name); //มองว่าเป็นuniqมีแค่1
    List<Menu> findByCategory(String category); //มีได้หลายอันเลยคืนเป็นlist
}
