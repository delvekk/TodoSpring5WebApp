package com.dawid.app.converters;

import com.dawid.app.commands.TodoCommand;
import com.dawid.app.domain.Todo;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class TodoToTodoCommand implements Converter<Todo, TodoCommand> {

    @Synchronized
    @Nullable
    @Override
    public TodoCommand convert(Todo source) {
        if (source == null) {
            return null;
        }

        final TodoCommand tdc = new TodoCommand();
        tdc.setId(source.getId());
        tdc.setTitle(source.getTitle());
        tdc.setLongDesc(source.getLongDesc());
        tdc.setPriority(source.getPriority());
        tdc.setStatus(source.getStatus());

        return tdc;
    }
}
