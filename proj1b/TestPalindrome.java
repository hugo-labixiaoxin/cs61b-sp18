import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    /*// You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.

    Uncomment this class once you've created your Palindrome class. */

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
    public void testisPalindrome() {
        String word1 = "a";
        String word2 = "";
        String word3 = "racecar";
        String word4 = "horse";
        String word5 = "rancor";
        assertTrue(palindrome.isPalindrome(word1));
        assertTrue(palindrome.isPalindrome(word2));
        assertTrue(palindrome.isPalindrome(word3));
        assertFalse(palindrome.isPalindrome(word4));
        assertFalse(palindrome.isPalindrome(word5));
    }

    @Test
    public void theoverloadingPalindrome() {
        CharacterComparator cc = new OffByOne();
        assertTrue(palindrome.isPalindrome("acegfdb",cc));
        assertTrue(palindrome.isPalindrome("flake",cc));
        assertTrue(palindrome.isPalindrome("a",cc));
        assertTrue(palindrome.isPalindrome("",cc));
        assertFalse(palindrome.isPalindrome("aaaab",cc));
        assertFalse(palindrome.isPalindrome("aaaaa",cc));
    }
}
