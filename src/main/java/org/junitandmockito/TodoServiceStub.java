/**
 * 
 */
package org.junitandmockito;

import java.util.Arrays;
import java.util.List;



/**
 * @author Prabhjeet Singh
 *
 *         Mar 1, 2022
 */
public  class TodoServiceStub implements TodoService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.training.testing.TodoService#retrieveTodos(java.lang.String)
	 */
	public List<String> retrieveTodos(String user) {
		// TODO Auto-generated method stub
		if (user.equals("Dummy"))
			return Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to dance");
		else if (user.equals("Dummy1"))
			return Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to dance");
		return null;
	}

	/* (non-Javadoc)
	 * @see org.training.testing.TodoService#deleteTodo(java.lang.String)
	 */
	public void deleteTodo(String todo) {
		// TODO Auto-generated method stub
		
	}

}
