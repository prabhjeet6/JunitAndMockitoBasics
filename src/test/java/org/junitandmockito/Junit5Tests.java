package org.junitandmockito;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

/**
 * @TestInstance(TestInstance.Lifecycle.PER_CLASS) -- Enables us to ask JUnit to
 * create only one instance of the test class and reuse it between tests.
 */
@TestInstance(TestInstance.Lifecycle.PER_METHOD) // Default
public class Junit5Tests {

	SystemUnderTest systemUnderTest;

	@Nested
	class AddTest {
		@Test
		public void testAddPositiveNumbers() {
			assertEquals(2, 1 + 1);
		}

		@Test
		public void testAddNegativeNumbers() {
			assertEquals(-2, (-1) + (-1));
		}

		/** assertAll(...) introduced in Junit5 with Lambda Capability */
		public void testMultiplyNumbers() {
			assertAll(() -> assertEquals(1, 1 * 1), () -> assertEquals(1, -1 * -1), () -> assertEquals(-1, -1 * 1));
		}

	}

	/** @BeforeEach is Junit 5 version. @Before is Junit4 counterpart */
	@BeforeEach
	public void setup() {
		System.out.println("Setup Before individual test");
		systemUnderTest = new SystemUnderTest();
	}

	@Test
	@EnabledOnOs(OS.WINDOWS) /*
								 * Allows to run Test Class only on a specific OS. Disabled for the rest of the
								 * Operating Systems.
								 */
	// @Disabled --Allows the Test Method to be skipped.
	@DisplayName("Provides Description for the Test: testDivideByZero")
	public void testDivideByZero() {
		System.out.println("Inside testDivideByZero");
		assertThrows(ArithmeticException.class, () -> systemUnderTest.arithmeticException());
	}

	/**
	 * @AfterEach is Junit 5 version. @After is Junit4 counterpart. Both of these
	 *            annotations take static methods as SUT instance needs to exist for
	 *            its class level setup to run before the tests run.
	 */
	@AfterEach
	public void teardown() {
		System.out.println("Cleanup After individual test");
	}

	/**
	 * @BeforeAll is Junit 5 version. @BeforeClass is Junit4 counterpart.Both of
	 *            these annotations take static methods as SUT instance needs to
	 *            exist for its class level setup to run before the tests run.
	 */
	@BeforeAll
	public static void setupTestClass() {
		System.out.println("Setup Before Test Class");
	}

	/** @AfterAll is Junit 5 version. @AfterClass is Junit4 counterpart */
	@AfterAll
	public static void teardownTestClass() {
		System.out.println("Cleanup After Test Class");
	}

}
