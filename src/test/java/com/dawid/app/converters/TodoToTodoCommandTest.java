package com.dawid.app.converters;

import com.dawid.app.commands.TodoCommand;
import com.dawid.app.domain.Todo;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TodoToTodoCommandTest {

    public static final Long ID = new Long(1L);
    public static final String TITLE = "title";
    TodoToTodoCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new TodoToTodoCommand();
    }

    @Test
    public void testNullObject() throws Exception{
        assertNull(converter.convert(null));
    }

    @Test
    public void convert() {
        Todo todo = new Todo();
        todo.setId(ID);
        todo.setTitle(TITLE);

        TodoCommand todoCommand = converter.convert(todo);

        assertEquals(ID,todoCommand.getId());
        assertEquals(TITLE,todoCommand.getTitle());

    }
}