


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Prabhjeet Singh
 *
 * Feb 27, 2022
 */
public class QuickBeforeAfterTest {
	/**
	 * Setup Test Data/Connections Before Each Test
	 */
	@Before
	public void setup() {
		System.out.println("Before Test");
	}
	
	@Test
	public void test1() {
		System.out.println("Test1 executed");
	}
	@Test
	public void test2() {
		System.out.println("Test2 executed");
	}
	/**
	 * TearDown Test Data/Connections Before Each Test
	 */
	@After
	public void tearDown() {
		System.out.println("Tear Down");
	}
	/**
	 * Setup Test Data/Connections at once, on class Level for all the Tests.
	 * Methods marked with @BeforeClass should be marked static.
	 */
	@BeforeClass
	public static void Before() {
		System.out.println("Before class");
	}

	/**
	 * Teardown Test Data/Connections at once, on class Level for all the Tests.
	 * Methods marked with @AfterClass should be marked static.
	 */
	@AfterClass
	public static void After() {
		System.out.println("After class");
	}

}
