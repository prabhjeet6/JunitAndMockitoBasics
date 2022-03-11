/**
 * 
 */
package org.junitandmockito;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Prabhjeet Singh
 *
 *TodoBusinessImpl : SUT(System under Test)
 *TodoService: dependency
 * Mar 1, 2022
 */
public class TodoBusinessImpl {
private TodoService todoService;

/**
 * @param todoService
 */
public TodoBusinessImpl(TodoService todoService) {
	super();
	this.todoService = todoService;
}
public List<String> retriveTdosRelatedToSpring(String user){
	List<String> filteredTodos=new ArrayList<String>();
	List<String> todos=todoService.retrieveTodos(user);
	for(String todo:todos) {
		if(todo.contains("Spring")) {
			filteredTodos.add(todo);
		}
	}	
		return filteredTodos;
	
	
}

public void deleteTodoNotRelatedToSpring(String user) {
	List<String> filteredTodos=new ArrayList<String>();
	List<String> todos=todoService.retrieveTodos(user);
	for(String todo:todos) {
		if(!todo.contains("Spring")) {
			todoService.deleteTodo(todo);
		}
	}	
}
}
