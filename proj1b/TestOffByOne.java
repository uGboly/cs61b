import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testequalChars(){
        assertFalse(offByOne.equalChars('a','a'));
        assertFalse(offByOne.equalChars('z','a'));
        assertFalse(offByOne.equalChars('a','z'));

        assertTrue(offByOne.equalChars('b','a'));
        assertTrue(offByOne.equalChars('d','e'));
        assertTrue(offByOne.equalChars('t','u'));
        assertTrue(offByOne.equalChars('b','c'));
    }
    // Your tests go here.
} //Uncomment this class once you've created your CharacterComparator interface and OffByOne class. **/