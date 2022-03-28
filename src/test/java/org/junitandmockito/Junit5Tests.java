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
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestReporter;
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
		@Tag("Math")
		@Test
		public void testAddPositiveNumbers() {
			assertEquals(2, 1 + 1);
		}

		@Tag("Math")
		@Test
		public void testAddNegativeNumbers() {
			assertEquals(-2, (-1) + (-1));
		}

		@Tag("Math")
		@Test /** assertAll(...) introduced in Junit5 with Lambda Capability */
		public void testMultiplyNumbers() {
			assertAll(() -> assertEquals(1, 1 * 1), () -> assertEquals(1, -1 * -1), () -> assertEquals(-1, -1 * 1));
		}

	}

	/**
	 * @RepeatedTest is used to run a test case multiple times. It takes an argument
	 *               that tells, how many number of times, the test has to repeat.
	 *               Interface RepetitionInfo's reference is passed in the test
	 *               method to access repetition count and other metadata.
	 */
	@RepeatedTest(3)
	public void testRepetition(RepetitionInfo repetitionInfo) {
		testReporter.publishEntry("Running " + testInfo.getTestMethod() + " of " + testInfo.getTestClass());
		System.out.println(repetitionInfo.getCurrentRepetition() + " of " + repetitionInfo.getTotalRepetitions());
	}

	/**
	 * TestInfo and TestReporter are interfaces for which different
	 * providers(implementations) are available. When used, their instances are
	 * dependency injected by Junit. TestInfo is used to provide Meta data for the
	 * Test Class and TestReporter provides a better reporting/Logging mechanism for
	 * tests.
	 */
	TestInfo testInfo;
	TestReporter testReporter;

	/** @BeforeEach is Junit 5 version. @Before is Junit4 counterpart */
	@BeforeEach
	public void setup(TestInfo testInfo, TestReporter testReporter) {
		this.testInfo = testInfo;
		this.testReporter = testReporter;
		System.out.println("Setup Before individual test");
		systemUnderTest = new SystemUnderTest();
	}

	@Test
	@EnabledOnOs(OS.WINDOWS) /*
								 * Allows to run Test Class only on a specific OS. Disabled for the rest of the
								 * Operating Systems.
								 */
	@Tag("Exceptions")
	/**
	 * @Tag is a repeatable annotation that is used to declare a tag for the
	 *      annotated test class or test method. Test plans to be run with specific
	 *      tags can be configured in Run Configurations.
	 * 
	 *      Tags are used to filter which tests are executed for a given test plan
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
