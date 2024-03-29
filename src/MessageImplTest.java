import static org.junit.Assert.*;

import org.junit.Test;

/**
 * JUnit Testing for the MessageImpl class. The purpose of this testing is to
 * ensure that when the message and counts have been set, that the results of
 * calling their respective getters display an accurate result.
 * 
 * @author Aidan O'Grady
 * @version 2.2
 * @since 0.3
 *
 */
public class MessageImplTest {

	Message message = new MessageImpl("abc123"); // New message.

	/**
	 * A simple test that merely ensures that the message String contained
	 * within the object is accurate.
	 */
	@Test
	public void testMessage(){ // Testing message is correct.
		String expected = "abc123"; // Expected value of message.
		assertTrue(message.getMessage().equals(expected));
	}
	
	/**
	 * A simple test to determine whether or not the character count is
	 * accurate.
	 */
	@Test
	public void testChararacterCount() { // Testing characterCount is correct.
		int expected = 6; // Expected value of characterCount.
		assertTrue(message.getCharacterCount() == expected);
	}
	
	/**
	 * A simple test to determine whether or not the digit count is accurate or
	 * not.
	 */
	@Test
	public void testDigitCount(){ // Testing digitCount is correct.
		int expected = 3; // Expected value of digitCount.
		assertTrue(message.getDigitCount() == expected);
	}

}
