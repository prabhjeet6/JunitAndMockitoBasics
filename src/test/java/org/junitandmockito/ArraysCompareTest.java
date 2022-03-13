package org.junitandmockito;
/**
 * 
 */


import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.junit.Test;

/**
 * @author Prabhjeet Singh
 *
 *         Feb 27, 2022
 */
public class ArraysCompareTest {

	/**
	 * AssertEquals(...) does not work here because reference comparison would be
	 * mad instead of Value comparison.
	 */
	@Test
	public void testArraySort_RandomArray() {
		int numbers[] = { 12, 3, 4, 1 };
		int expected[] = { 1, 3, 4, 12 };
		Arrays.sort(numbers);
		assertArrayEquals(expected, numbers);
	}

	@Test(expected = NullPointerException.class)
	public void testArraySort_NullArray() {
		int numbers[] = null;
		Arrays.sort(numbers);
	}

	/**
	 * Test performance of Heavy operrations. Fails if there is a timeout with a lag
	 * of mentioned limit of milliseconds, below which the lag is acceptable.
	 */
	@Test(timeout = 100)
	public void testSort_Performance() {

		int numbers[] = { 12, 23, 4 };
		for (int i = 0; i < 1000000; i++) {
			numbers[0] = i;
			Arrays.sort(numbers);
		}
	}
}
