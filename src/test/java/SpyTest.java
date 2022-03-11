/**
 * 
 */


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @author Prabhjeet Singh
 *
 *         Mar 4, 2022
 */

/**
 * Spy is a partial mock. It gets all the functionality of the class being spied,
 * It also allows you to override the behavior of spied object via stubbing.
 */
public class SpyTest {

	@Test
	public void test() {
		List arrayListSpy = spy(ArrayList.class);
		assertEquals(0, arrayListSpy.size());

		arrayListSpy.add("Dummy");
		assertEquals(1, arrayListSpy.size());

		arrayListSpy.remove("Dummy");
		assertEquals(0, arrayListSpy.size());

	}

	@Test
	public void test1() {
		List arrayListSpy = spy(ArrayList.class);
		when(arrayListSpy.size()).thenReturn(5);
		assertEquals(5, arrayListSpy.size());
	}

}
