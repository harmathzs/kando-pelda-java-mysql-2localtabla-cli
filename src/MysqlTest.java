import com.mysql.cj.xdevapi.JsonParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

        Assertions.assertNotNull(results);
    }

    @Test
    public void testqueryDogsWithIds() {
        Set<Integer> dogIds = new HashSet<>();
        dogIds.add(1);
        dogIds.add(2);

        List<Dog> dogs = MysqlService.queryDogs("dogs_and_owners", "root", "", dogIds);
        System.out.println(dogs); // [Dog@263121b, Dog@41dabe47]

        Assertions.assertEquals(2, dogs.size());
    }
    @Test
    public void testqueryDogsWithoutIds() {

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
