package org.junitandmockito;
/**
 * 
 */


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

/**
 * @author Prabhjeet Singh
 *
 *         Mar 1, 2022
 */
public class TodoBusinessImplMockTest {

	@Test
	public void testRetrieveTodosRelatedToSpring_usingAMock() {
		TodoService mockTodoService = mock(TodoService.class);
		when(mockTodoService.retrieveTodos("Dummy"))
				.thenReturn(Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to dance"));
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(mockTodoService);
		List<String> filteredTodos = todoBusinessImpl.retriveTdosRelatedToSpring("Dummy");
		assertEquals(2, filteredTodos.size());
	}

	@Test
	public void testRetrieveTodosRelatedToSpring_usingBDD() {
		// Given -setup
		TodoService mockTodoService = mock(TodoService.class);
		given(mockTodoService.retrieveTodos("Dummy"))
				.willReturn(Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to dance"));
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(mockTodoService);
		// When
		List<String> filteredTodos = todoBusinessImpl.retriveTdosRelatedToSpring("Dummy");
		// Then
		// assertEquals(2, filteredTodos.size());
		assertThat(filteredTodos.size(), is(2));
	}

	@Test
	public void testRetrieveTodosRelatedToSpring_withEmptyList() {
		TodoService mockTodoService = mock(TodoService.class);
		when(mockTodoService.retrieveTodos("Dummy")).thenReturn(Arrays.asList(new String[0]));
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(mockTodoService);
		List<String> filteredTodos = todoBusinessImpl.retriveTdosRelatedToSpring("Dummy1");
		assertEquals(0, filteredTodos.size());
	}

	@Test
	public void testDeleteNotRelatedToSpring_UsingBDD() {
		// Given -setup
		TodoService mockTodoService = mock(TodoService.class);
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to dance");
		given(mockTodoService.retrieveTodos("Dummy")).willReturn(todos);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(mockTodoService);
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

		// Declare Argument Captor
		ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

		// Given -setup
		TodoService mockTodoService = mock(TodoService.class);
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to dance");
		given(mockTodoService.retrieveTodos("Dummy")).willReturn(todos);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(mockTodoService);
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
