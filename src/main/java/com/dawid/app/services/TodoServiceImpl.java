package com.dawid.app.services;

import com.dawid.app.commands.TodoCommand;
import com.dawid.app.converters.TodoCommandToTodo;
import com.dawid.app.converters.TodoToTodoCommand;
import com.dawid.app.domain.Todo;
import com.dawid.app.exceptions.NotFoundException;
import com.dawid.app.repositories.TodoRepository;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final TodoCommandToTodo todoCommandToTodo;
    private final TodoToTodoCommand todoToTodoCommand;

    public TodoServiceImpl(TodoRepository todoRepository, TodoCommandToTodo todoCommandToTodo, TodoToTodoCommand todoToTodoCommand) {
        this.todoRepository = todoRepository;
        this.todoCommandToTodo = todoCommandToTodo;
        this.todoToTodoCommand = todoToTodoCommand;

    }

    @Override
    public List<Todo> getTodoItems() {

        List<Todo> todoArray = new ArrayList<>();
        todoRepository.findAll().iterator().forEachRemaining(todoArray::add);
        Collections.sort(todoArray, Todo::compareTo);
        return todoArray;

    }

    @Override
    public Todo findById(Long id) {
        Optional<Todo> todoOptional = todoRepository.findById(id);

        if (!todoOptional.isPresent()) {
            throw new NotFoundException("Todo Not Found");
        }

        return todoOptional.get();
    }

    @Override
    public TodoCommand saveTodoItem(TodoCommand command) {
        Todo todo = todoCommandToTodo.convert(command);

        Todo savedTodo = todoRepository.save(todo);

        return todoToTodoCommand.convert(savedTodo);
    }

    @Override
    public TodoCommand findCommandById(Long l) {
        return todoToTodoCommand.convert(findById(l));
    }

    @Override
    public void deleteAll() {
        todoRepository.deleteAll();
    }

    @Override
    public void deleteById(Long id) {
        todoRepository.deleteById(id);
    }
}
