package org.junitandmockito;
/**
 * 
 */


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;

/**
 * @author Prabhjeet Singh
 *
 *         Mar 3, 2022
 */
public class ListTest {
	List<String> mockList = mock(List.class);

	@Test
	public void testMockListSizeMethod_ReturnMultipleValues() {

		when(mockList.size()).thenReturn(2).thenReturn(3);
		assertEquals(2, mockList.size());
		assertEquals(3, mockList.size());
	}

	@Test
	public void testMockListGetMethod() {
		when(mockList.get(0)).thenReturn("Test");
		assertEquals("Test", mockList.get(0));
		// Argument Matcher
		assertEquals("Test", mockList.get(anyInt()));
		// when stubbing is not avaialable for a particular method call, default value
		// is returned.
		assertEquals(null, mockList.get(1));
	}
	@Test
	public void testMockListGetMethod_usingBDD() {
		//Given
		when(mockList.get(0)).thenReturn("Test");
		//When
		mockList.get(0);
		//Then
		assertThat(mockList.get(0), is("Test"));
	}

	@Test (expected=RuntimeException.class)
	public void testMockListGetMethod_ThrowsException() {
		when(mockList.get(anyInt())).thenThrow(new RuntimeException("Exception occured"));
		mockList.get(0);
		// Without expected Tag, the test would fail and RunTimeException would be
		// thrown.
	}
	
	@Test 
	public void testMockList_MixArgumentMatcherWithHardCodedValue() {
		//Invalid use of Matchers, you can not mix Argument Matchers with Hardcode values
		when(mockList.subList(anyInt(), 5)).thenThrow(new RuntimeException("Exception occured"));
		mockList.subList(6, 5);
	}

}
