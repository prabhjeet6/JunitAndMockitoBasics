package org.junitandmockito;



import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

/**
 * @author Prabhjeet Singh
 *
 *         Mar 4, 2022
 */
public class HamcrestMatchersTest {

	List<Integer> scores = Arrays.asList(99, 100, 101, 105);

	@Test
	public void test() {
		assertThat(scores, hasSize(4));
		assertThat(scores, hasItems(99, 100));
		assertThat(scores, everyItem(greaterThan(90)));
		assertThat(scores, everyItem(lessThan(190)));

		// String
		assertThat("", isEmptyString());
		assertThat(null, isEmptyOrNullString());

		// Array
		Integer[] marks = { 1, 2, 3 };
		assertThat(marks, arrayWithSize(3));
		// All the elements should be present in arrayContaining(...) matcher in the
		// same order.
		assertThat(marks, arrayContaining(1, 2, 3));

		assertThat(marks, arrayContainingInAnyOrder(2, 1, 3));
	}

}
