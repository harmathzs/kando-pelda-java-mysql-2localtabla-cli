import com.mysql.cj.xdevapi.JsonParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

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
        Set<Integer> dogIds = new HashSet<>(Arrays.asList(1, 2));

        List<Dog> dogs = MysqlService.queryDogs("dogs_and_owners", "root", "", dogIds);
        System.out.println(dogs); // [Dog@263121b, Dog@41dabe47]

        Assertions.assertEquals(2, dogs.size());
    }
    @Test
    public void testqueryDogsWithoutIds() {
        List<Dog> dogs = MysqlService.queryDogs("dogs_and_owners", "root", "", Collections.emptySet());
        System.out.println(dogs); // [Dog@691ff4fe, Dog@3d426638, Dog@1b6a9bb9]

        Assertions.assertFalse(dogs.isEmpty());
    }

    @Test
    public void testqueryOwnersWithIds() {
        Set<Integer> ownerIds = new HashSet<>(Arrays.asList(1,2));
        List<Owner> owners = MysqlService.queryOwners("dogs_and_owners", "root", "", ownerIds);
        System.out.println(owners);
        Assertions.assertFalse(owners.isEmpty());
        Assertions.assertEquals(2, owners.size());
    }

    @Test
    public void testqueryOwnersWithoutIds() {
        List<Owner> owners = MysqlService.queryOwners("dogs_and_owners", "root", "", Collections.emptySet());
        System.out.println(owners);
        Assertions.assertFalse(owners.isEmpty());
    }

    @Test
    public void testupsertDogs() {
        Dog[] dogs = new Dog[2];
        dogs[0] = new Dog(1, "Fido", 1, true, null);
        dogs[1] = new Dog(2, "Fifi", 2, false, null);
        MysqlService.upsertDogs(dogs);
    }

    @Test
    public void testupsertOwners() {
        Owner[] owners = new Owner[2];
        owners[0] = new Owner(1, "John Smith");
        owners[1] = new Owner(2, "Jane Doe");
        MysqlService.upsertOwners(owners);
    }

    @Test
    public void testdeleteDogs() {
        testupsertDogs();
        Set<Integer> dogIds = new HashSet<>(Arrays.asList(1,2));
        MysqlService.deleteDogs(dogIds);
    }

    @Test
    public void testdeleteOwners() {
        testupsertOwners();
        Set<Integer> ownerIds = new HashSet<>(Arrays.asList(1,2));
        MysqlService.deleteOwners(ownerIds);
    }
}
