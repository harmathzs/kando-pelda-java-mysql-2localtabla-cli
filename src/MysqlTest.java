import org.junit.jupiter.api.Test;

public class MysqlTest {
    @Test
    public void testgetDogsAndOwners() {
        MysqlService.getDogsAndOwners("dogs_and_owners", "root", "");
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
