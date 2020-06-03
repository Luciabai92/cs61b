import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testisPalindrome(){
        assertFalse(palindrome.isPalindrome("cat"));
        assertTrue(palindrome.isPalindrome("noon"));
        assertTrue(palindrome.isPalindrome("racecar"));
    }

    @Test
    public void testSpecialPalindrome(){
        assertFalse(palindrome.isPalindrome("Noon"));
        assertTrue(palindrome.isPalindrome(" "));
        assertTrue(palindrome.isPalindrome("n"));
    }

    @Test
    public void testOffByOnePalindrome(){
        OffByOne obo = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake",obo));
        assertTrue(palindrome.isPalindrome("abb",obo));
        assertTrue(palindrome.isPalindrome("a",obo));
        assertFalse(palindrome.isPalindrome("abc",obo));
    }

}