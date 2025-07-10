import com.mysql.cj.xdevapi.JsonParser;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class MysqlTest {
    @Test
    public void testgetDogsAndOwners() {
        Map<String, List<Map<String, Object>>> results = MysqlService.getDogsAndOwners("dogs_and_owners", "root", "");
        System.out.println(results);
        // {dogs=[
            // {dog_name=Fido, ownerid=1, age=3.0, male=true, dog_id=1},
            // {dog_name=Fifi, ownerid=2, age=1.0, male=false, dog_id=2},
            // {dog_name=Oscar, ownerid=1, age=4.0, male=true, dog_id=3}],
        // owners=[
            // {owner_name=John Smith, owner_id=1},
            // {owner_name=Jane Doe, owner_id=2},
            // {owner_name=John Smith, owner_id=1},
            // {owner_name=Mia Gordon, owner_id=3}]}

        // TODO - Owners can appear duplicated! See: John Smith
    }

    @Test
    public void testupsertDogs() {
        MysqlService.upsertDogs();
    }

    @Test
    public void testupsertOwners() {
        MysqlService.upsertOwners();
    }

    @Test
    public void testdeleteDogs() {
        MysqlService.deleteDogs();
    }

    @Test
    public void testdeleteOwners() {
        MysqlService.deleteOwners();
    }
}
