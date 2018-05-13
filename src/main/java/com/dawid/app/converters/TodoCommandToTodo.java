package com.dawid.app.converters;

import com.dawid.app.commands.TodoCommand;
import com.dawid.app.domain.Todo;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class TodoCommandToTodo implements Converter<TodoCommand, Todo> {

    @Synchronized
    @Nullable
    @Override
    public Todo convert(TodoCommand source) {
        if (source == null) {
            return null;
        }

        final Todo todo = new Todo();
        todo.setId(source.getId());
        todo.setTitle(source.getTitle());
        todo.setLongDesc(source.getLongDesc());
        todo.setPriority(source.getPriority());
        todo.setStatus(source.getStatus());

        return todo;
    }
}
