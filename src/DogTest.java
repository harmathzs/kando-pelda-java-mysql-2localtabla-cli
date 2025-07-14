import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DogTest {
    @Test
    public void testDog() {
        Dog fido = new Dog(
                1, "Fido",
                3, true,
                null
        );
        Assertions.assertEquals(1, fido.getId());
        Assertions.assertEquals("Fido", fido.getName());
        Assertions.assertEquals(3, fido.getAge());
        Assertions.assertTrue(fido.isMale());
        Assertions.assertNull(fido.getOwner());
    }
}
