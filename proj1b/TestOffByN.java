import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    static CharacterComparator offByN = new OffByN(5);

    @Test
    public void testEqualChars(){
        assertTrue(offByN.equalChars('a','f'));
        assertTrue(offByN.equalChars('b','g'));
        assertFalse(offByN.equalChars('a','c'));
        assertFalse(offByN.equalChars('a','a'));
    }
}
