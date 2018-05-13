package com.dawid.app.services;

import com.dawid.app.commands.TodoCommand;
import com.dawid.app.converters.TodoCommandToTodo;
import com.dawid.app.converters.TodoToTodoCommand;
import com.dawid.app.domain.Todo;
import com.dawid.app.repositories.TodoRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TodoServiceImplTest {

    TodoService todoService;

    @Mock
    TodoRepository repository;

    @Mock
    TodoToTodoCommand todoToTodoCommand;

    @Mock
    TodoCommandToTodo todoCommandToTodo;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        todoService = new TodoServiceImpl(repository,todoCommandToTodo,todoToTodoCommand);
    }

    @Test
    public void getTodoItemsTest() throws Exception {
        Todo todo1 = new Todo();
        todo1.setId(1L);
        todo1.setTitle("Desc");

        Todo todo2 = new Todo();
        todo2.setId(2L);
        todo2.setTitle("Desc2");

        when(repository.findAll()).thenReturn(Arrays.asList(todo1,todo2));

        List<Todo> list = todoService.getTodoItems();

        assertEquals(2,list.size());


    }

    @Test
    public void findByIdTest() throws Exception{
        Todo todo = new Todo();
        todo.setId(1L);

        Optional<Todo> todoOptional = Optional.of(todo);

        when(repository.findById(anyLong())).thenReturn(todoOptional);

        Todo returnTodo = todoService.findById(anyLong());

        verify(repository,times(1)).findById(anyLong());
        assertEquals(todo.getId(), returnTodo.getId());

    }

    @Test
    public void saveTodoItemTest() throws Exception{
        TodoCommand todoCommand = new TodoCommand();
        todoCommand.setId(1L);

        Todo todo = new Todo();
        todo.setId(1L);

        TodoCommand returnedCommand = new TodoCommand();
        returnedCommand.setId(1L);

        when(repository.save(any())).thenReturn(todo);
        when(todoCommandToTodo.convert(any())).thenReturn(todo);
        when(todoToTodoCommand.convert(any())).thenReturn(returnedCommand);

        TodoCommand command = todoService.saveTodoItem(todoCommand);

        assertEquals(Long.valueOf(1),command.getId());

    }

}