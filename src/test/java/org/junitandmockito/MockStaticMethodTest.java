package org.junitandmockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @Mock annotation is used along with MockitoJUnitRunner
 * 
 *       Powermock is not well supported with Java 8+, 'mockito-inline'
 *       dependency is added that provides mockito's support for testing static
 *       methods.
 * 
 *       Previous to version 3.4.0 of Mockito, it wasn't possible to mock static
 *       methods directly — only with the help of PowerMockito.
 * 
 *       since Mockito 3.4.0, we can use the Mockito.mockStatic(Class<T>
 *       classToMock) method to mock invocations to static method calls. This
 *       method returns a MockedStatic object for our type, which is a scoped
 *       mock object.
 * 
 *       Generally speaking, some might say that when writing clean
 *       object-orientated code, we shouldn't need to mock static classes. This
 *       could typically hint at a design issue or code smell in our
 *       application.
 * 
 *       Why? First, a class depending on a static method has tight coupling,
 *       and second, it nearly always leads to code that is difficult to test.
 *       Ideally, a class should not be responsible for obtaining its
 *       dependencies, and if possible, they should be externally injected.
 **/
@RunWith(MockitoJUnitRunner.class)

public class MockStaticMethodTest {

	@Mock
	Dependency dependency;

	@InjectMocks
	SystemUnderTest systemUnderTest;

	@Test
	public void testStaticMethod() {
		when(dependency.retrieveAllStats()).thenReturn(Arrays.asList(1, 2, 3));

		try (MockedStatic<UtilityClass> utilityClass = Mockito.mockStatic(UtilityClass.class)) {
			utilityClass.when(() -> UtilityClass.staticMethod(6)).thenReturn(150);

			int result = systemUnderTest.methodCallingAStaticMethod();
			assertEquals(150, result);
		}
	}

}
