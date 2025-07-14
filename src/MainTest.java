import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MainTest {
    @BeforeAll
    public static void testBeforeAll() {
        Main.isRunningTest = true;
    }
    @AfterAll
    public static void testAfterAll() {
        Main.isRunningTest = false;
    }

    @Test
    public void testMain() {
        Main.main(null);
    }
}
