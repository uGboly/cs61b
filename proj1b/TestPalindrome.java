import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

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
        CharacterComparator offByOne = new OffByOne();
        CharacterComparator offBy5 = new OffByN(5);

        assertTrue(palindrome.isPalindrome("ugboobgu"));
        assertFalse(palindrome.isPalindrome("ugboly"));

        assertTrue(palindrome.isPalindrome("binding",offBy5));
        assertTrue( palindrome.isPalindrome("detrude",offByOne));

    }

}     //Uncomment this class once you've created your Palindrome class.