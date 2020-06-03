import static org.junit.Assert.*;
import org.junit.Test;

public class FlikTest {
    @Test
    public void testFlik(){
        int a = 128;
        int b = 128;
        boolean actual = Flik.isSameNumber(a,b);
        assertTrue(actual);
        int c = 99;
        actual = Flik.isSameNumber(a,c);
        assertFalse(actual);
    }
}
