import org.junit.jupiter.api.*;

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
    public void testMainChoice1() {
        Main.choice = 1;
        Main.main(null);
    }
    @Test
    public void testMainChoice2() {
        Main.choice = 2;
        Main.main(null);
    }
    @Test
    public void testMainChoice3() {
        Main.choice = 3;
        Main.main(null);
    }
    @Test
    public void testMainChoice4() {
        Main.choice = 4;
        Main.main(null);
    }
    @Test
    public void testMainChoice5() {
        Main.choice = 5;
        Main.main(null);
    }
    @Test
    public void testMainChoice6() {
        Main.choice = 6;
        Main.main(null);
    }
    @Test
    public void testMainChoice7() {
        Main.choice = 7;
        Main.main(null);
    }
}
