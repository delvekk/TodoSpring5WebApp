package com.dawid.app.converters;

import com.dawid.app.commands.TodoCommand;
import com.dawid.app.domain.Todo;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TodoCommandToTodoTest {

    public static final Long ID = new Long(1L);
    public static final String TITLE = "title";
    TodoCommandToTodo converter;

    @Before
    public void setUp() throws Exception {
        converter = new TodoCommandToTodo();

    }

    @Test
    public void testNullObject() throws Exception{
        assertNull(converter.convert(null));
    }

    @Test
    public void convert() throws Exception{
        TodoCommand todoCommand = new TodoCommand();
        todoCommand.setId(ID);
        todoCommand.setTitle(TITLE);

        Todo todo = converter.convert(todoCommand);

        assertEquals(ID, todo.getId());
        assertEquals(TITLE, todo.getTitle());
    }

}