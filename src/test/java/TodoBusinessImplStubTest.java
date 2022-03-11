/**
 * 
 */


import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junitandmockito.TodoBusinessImpl;
import org.junitandmockito.TodoService;
import org.junitandmockito.TodoServiceStub;


/**
 * @author Prabhjeet Singh
 *
 *         Mar 1, 2022
 */
public class TodoBusinessImplStubTest {

	@Test
	public void testRetrieveTodosRelatedToSpring_usingAStub() {
		TodoService todoServiceStub = new TodoServiceStub();
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceStub);
		List<String> filteredTodos = todoBusinessImpl.retriveTdosRelatedToSpring("Dummy");
		assertEquals(2, filteredTodos.size());
	}
	
	@Test
	public void testRetrieveTodosRelatedToSpring_usingAStub2() {
		TodoService todoServiceStub = new TodoServiceStub();
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceStub);
		List<String> filteredTodos = todoBusinessImpl.retriveTdosRelatedToSpring("Dummy1");
		assertEquals(0, filteredTodos.size());
	}
}
