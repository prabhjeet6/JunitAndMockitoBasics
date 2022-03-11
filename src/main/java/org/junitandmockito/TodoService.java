
package org.junitandmockito;

import java.util.List;

/**
 * @author Prabhjeet Singh
 *
 * Mar 1, 2022
 */
public interface TodoService {

	public List<String> retrieveTodos(String user);
	
	public void deleteTodo(String todo);
}
