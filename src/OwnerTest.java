import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OwnerTest {
    @Test
    public void testOwner() {
        Owner janeDoe = new Owner(1, "Jane Doe");
        Assertions.assertEquals(1, janeDoe.getId());
        Assertions.assertEquals("Jane Doe", janeDoe.getName());
    }
}
