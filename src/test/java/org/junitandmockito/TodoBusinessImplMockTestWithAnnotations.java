package org.junitandmockito;

/**
 * 
 */

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 * @author Prabhjeet Singh
 *
 *         Mar 1, 2022
 */

// @RunWith(MockitoJUnitRunner.class)
public class TodoBusinessImplMockTestWithAnnotations {
	/**
	 * A Collaborator class('TodoService' in this case) is one, which is mocked to
	 * test the SUT('TodoBusinessImpl' in this case).
	 */
	@Mock
	TodoService mockTodoService;

	@InjectMocks
	TodoBusinessImpl todoBusinessImpl;

	@Captor
	ArgumentCaptor<String> argumentCaptor;
	/**
	 * We can't have more than one runners in a test case, hence @Rule was
	 * introduced to solve this problem. We can have multiple rules and rules should
	 * be declared as public. Similar to a runner, rule runs before and after the
	 * test
	 */
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Test
	public void testRetrieveTodosRelatedToSpring_usingAMock() {
		when(mockTodoService.retrieveTodos("Dummy"))
				.thenReturn(Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to dance"));
		/*
		 * @InjectMocks does the job of injecting dependency mocks and below code
		 * snippet is not required. TodoBusinessImpl todoBusinessImpl = new
		 * TodoBusinessImpl(mockTodoService);
		 */
		List<String> filteredTodos = todoBusinessImpl.retriveTdosRelatedToSpring("Dummy");
		assertEquals(2, filteredTodos.size());
	}

	@Test
	public void testRetrieveTodosRelatedToSpring_usingBDD() {
		// Given -setup
		given(mockTodoService.retrieveTodos("Dummy"))
				.willReturn(Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to dance"));
		/*
		 * @InjectMocks does the job of injecting dependency mocks and below code
		 * snippet is not required. TodoBusinessImpl todoBusinessImpl = new
		 * TodoBusinessImpl(mockTodoService);
		 */
		// When
		List<String> filteredTodos = todoBusinessImpl.retriveTdosRelatedToSpring("Dummy");
		// Then
		// assertEquals(2, filteredTodos.size());
		assertThat(filteredTodos.size(), is(2));
	}

	@Test
	public void testRetrieveTodosRelatedToSpring_withEmptyList() {
		when(mockTodoService.retrieveTodos("Dummy")).thenReturn(Arrays.asList(new String[0]));
		/*
		 * @InjectMocks does the job of injecting dependency mocks and below code
		 * snippet is not required. TodoBusinessImpl todoBusinessImpl = new
		 * TodoBusinessImpl(mockTodoService);
		 */
		List<String> filteredTodos = todoBusinessImpl.retriveTdosRelatedToSpring("Dummy1");
		assertEquals(0, filteredTodos.size());
	}

	@Test
	public void testDeleteNotRelatedToSpring_UsingBDD() {
		// Given -setup
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to dance");
		given(mockTodoService.retrieveTodos("Dummy")).willReturn(todos);
		/*
		 * @InjectMocks does the job of injecting dependency mocks and below code
		 * snippet is not required. TodoBusinessImpl todoBusinessImpl = new
		 * TodoBusinessImpl(mockTodoService);
		 */
		// When
		todoBusinessImpl.deleteTodoNotRelatedToSpring("Dummy");
		// Then
		/**
		 * Verifies certain behavior on the mock, in this particular example,
		 * verify(...) checks when
		 * "todoBusinessImpl.deleteTodoNotRelatedToSpring("Dummy");" is called,wether a
		 * particular behaviour on the mock is observed.
		 */
		verify(mockTodoService).deleteTodo("Learn to dance");
		// Or, a similar statement would be
		then(mockTodoService).should().deleteTodo("Learn to dance");

		verify(mockTodoService, never()).deleteTodo("Learn Spring MVC");
		// Or, a similar statement would be
		then(mockTodoService).should(never()).deleteTodo("Learn Spring MVC");
		verify(mockTodoService, times(1)).deleteTodo("Learn to dance");

	}

	@Test
	public void testDeleteNotRelatedToSpring_UsingBDD_ArgumentCapture() {

		// Declare Argument Captor : @Captor does the declaration automatically
		// ArgumentCaptor<String> argumentCaptor =
		// ArgumentCaptor.forClass(String.class);

		// Given -setup
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to dance");
		given(mockTodoService.retrieveTodos("Dummy")).willReturn(todos);
		/*
		 * @InjectMocks does the job of injecting dependency mocks and below code
		 * snippet is not required. TodoBusinessImpl todoBusinessImpl = new
		 * TodoBusinessImpl(mockTodoService);
		 */
		// When
		todoBusinessImpl.deleteTodoNotRelatedToSpring("Dummy");
		// Then
		// instead of verifying for different values separately, we capture the argument
		// and compare with the expected value.
		then(mockTodoService).should().deleteTodo(argumentCaptor.capture());
		assertThat(argumentCaptor.getValue(), is("Learn to dance"));

		/*
		 * if there are multiple calls with same argument, then,
		 * then(mockTodoService).should(times(2)).deleteTodo(argumentCaptor.capture());
		 * assertThat(argumentCaptor.getAllValues().size(), is(2));
		 */

	}

}
