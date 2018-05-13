package com.dawid.app.services;

import com.dawid.app.commands.TodoCommand;
import com.dawid.app.domain.Todo;

import java.util.List;
public interface TodoService {

    List<Todo> getTodoItems();

    Todo findById(Long id);

    TodoCommand saveTodoItem(TodoCommand command);

    TodoCommand findCommandById(Long l);

    void deleteAll();

    void deleteById(Long id);



}
