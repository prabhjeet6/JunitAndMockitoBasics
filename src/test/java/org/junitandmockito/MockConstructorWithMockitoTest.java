package org.junitandmockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

/**
 * Starting with Mockito version 3.5.0, we can now mock Java constructors with
 * Mockito. It's the next step towards replacing PowerMock with Mockito.This new
 * feature of Mockito is only available if we use an InlineMockMaker. The
 * InlineMockMaker is an interface that extends the MockMaker interface that
 * defines Mockito's internal API to create mocks. There are multiple
 * implementations for this interface, and the default one is the
 * SubclassByteBuddyMockMaker.To override the default MockMaker and use a
 * InlineMockMaker instead, we can replace our mockito-core dependency with
 * mockito-inline.
 */
public class MockConstructorWithMockitoTest {

	/**
	 * The new method that makes mocking object constructions possible is
	 * Mockito.mockConstruction(). This method takes a non-abstract Java class that
	 * constructions we're about to mock as a first argument. In the example above,
	 * we use an overloaded version of mockConstruction() to pass a MockInitializer
	 * as a second argument. This MockInitializer is a callback that we use to stub
	 * the behavior of the mock.
	 * 
	 * We can define the scope of mocking any object creation for our UtilityClass
	 * by using Java's try-with-resources construct, as the MockedConstruction is
	 * extending the UtilityClass interface. This allows mocking the object
	 * construction only for a temporary and user-defined purpose.
	 */
	@Test
	public void mockObjectConstruction() {
		try (MockedConstruction<UtilityClass> mocked = Mockito.mockConstruction(UtilityClass.class, (mock, context) -> {
			when(mock.dummyMethod(anyString(), any(BigDecimal.class))).thenReturn(BigDecimal.TEN);
		})) {
			UtilityClass utilityClass = new UtilityClass();
			BigDecimal result = utilityClass.dummyMethod("dummyValue", BigDecimal.ONE);
			assertEquals(BigDecimal.TEN, result);
		}
	}

	/**
	 * Whenever we use the try-with-resources (highly recommended for this use case)
	 * construct, every constructor call inside this block is mocked. Any object
	 * creation before or after returns a real instance. Furthermore, as all object
	 * constructions for this class are mocked, it doesn't matter which public
	 * constructor we use.
	 */
	@Test
	public void mockDifferentObjectConstruction() {
		try (MockedConstruction<UtilityClass> mocked = Mockito.mockConstruction(UtilityClass.class)) {

			UtilityClass firstInstance = new UtilityClass("PayPal", BigDecimal.TEN);
			UtilityClass secondInstance = new UtilityClass(true, "PayPal", BigDecimal.TEN);

			when(firstInstance.dummyMethod(anyString(), any(BigDecimal.class))).thenReturn(BigDecimal.TEN);
			when(secondInstance.dummyMethod(anyString(), any(BigDecimal.class))).thenReturn(BigDecimal.TEN);

			assertEquals(BigDecimal.TEN, firstInstance.dummyMethod("42", BigDecimal.valueOf(42)));
			assertEquals(BigDecimal.TEN, secondInstance.dummyMethod("42", BigDecimal.valueOf(42)));
			assertEquals(2, mocked.constructed().size());
		}
	}
}
