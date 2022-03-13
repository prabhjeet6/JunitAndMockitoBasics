package org.junitandmockito;

import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/** @Mock annotation is used along with MockitoJUnitRunner **/
@RunWith(MockitoJUnitRunner.class)

public class MockStaticMethodTest {

	@Mock
	Dependency dependency;

	@InjectMocks
	SystemUnderTest systemUnderTest;

	
	@Test
	public void testStaticMethod() {
     when(dependency.retrieveAllStats()).thenReturn(Arrays.asList(1,2,3));
     
     try (MockedStatic<UtilityClass> utilityClass = Mockito.mockStatic(UtilityClass.class)) {
    	 utilityClass.when(()->UtilityClass.staticMethod(6)).thenReturn(150);
     
     systemUnderTest.methodCallingAStaticMethod();
     }
	}

}
