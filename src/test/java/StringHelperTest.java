/**
 * 
 */


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junitandmockito.StringHelper;

/**
 * @author Prabhjeet Singh
 *
 *         Feb 25, 2022
 */

public class StringHelperTest {

	// AACD=>CD ACD=>CD CDEF=>CDEF CDAA=>CDAA

	StringHelper helper = new StringHelper();

	@Test
	public void testTruncateAInFirst2Positions_AinFirst2Positions() {
		assertEquals("CD", helper.truncateAInFirst2Positions("AACD"));
	}

	@Test
	public void testTruncateAInFirst2Positions_Parameterized() {
		assertEquals("CD", helper.truncateAInFirst2Positions("ACD"));
	}

	@Test
	public void testAreFirstAndLastTwoCharactersTheSame_BasicNegativeScenario() {
		assertFalse("The Condition Failed", helper.areFirstAndLastTwoCharactersTheSame("ABCD"));
	}
}
