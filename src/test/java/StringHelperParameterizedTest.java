/**
 * 
 */


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junitandmockito.StringHelper;

/**
 * @author Prabhjeet Singh Feb 25, 2022
 * 
 *         Parameterized tests allow a developer to run the same test over and
 *         over again using different values.
 */
@RunWith(Parameterized.class)
public class StringHelperParameterizedTest {

	@Parameters
	public static Collection<String[]> testConditions() {
		String expectedOutputs[][] = { { "AACD", "CD" }, { "ACD", "CD" } };
		return Arrays.asList(expectedOutputs);
	}

	// AACD=>CD ACD=>CD CDEF=>CDEF CDAA=>CDAA

	StringHelper helper = new StringHelper();

	private String input;
	private String expectedOutput;

	/**
	 * @param input
	 * @param expectedOutput
	 */
	public StringHelperParameterizedTest(String input, String expectedOutput) {
		super();
		this.input = input;
		this.expectedOutput = expectedOutput;
	}

	@Test
	public void testTruncateAInFirst2Positions_AinFirstPosition() {
		assertEquals(expectedOutput, helper.truncateAInFirst2Positions(input));
	}

	@Test
	public void testAreFirstAndLastTwoCharactersTheSame_BasicNegativeScenario() {
		assertFalse("The Condition Failed", helper.areFirstAndLastTwoCharactersTheSame(input));
	}
}
