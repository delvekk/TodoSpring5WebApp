package com.dawid.app.controllers;


import com.dawid.app.commands.TodoCommand;
import com.dawid.app.services.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URL;

@Slf4j
@Controller
public class TodoController {

    private final String URL_FORM = "todo/todoform";
    private final TodoService todoService;


    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }


    @GetMapping("/todoitem/{id}/show")
    public String getTodoItemById(@PathVariable String id, Model model) {
        model.addAttribute("todoitem", todoService.findById(Long.valueOf(id)));

        return "todo/show";
    }


    @GetMapping("/todoitem/new")
    public String addTodoItem(Model model) {
        model.addAttribute("todoitem", new TodoCommand());

        return URL_FORM;
    }


    @GetMapping("/todoitem/{id}/update")
    public String updateTodoItem(@PathVariable String id, Model model) {
        model.addAttribute("todoitem", todoService.findCommandById(Long.valueOf(id)));

        return URL_FORM;
    }

    @PostMapping("todo")
    public String save(@Valid @ModelAttribute("todoitem") TodoCommand command, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return URL_FORM;
        }

        TodoCommand savedCommand = todoService.saveTodoItem(command);

        return "redirect:/todoitem/" + savedCommand.getId() + "/show";
    }

    @GetMapping("deleteall")
    public String deleteAll() {
        todoService.deleteAll();

        return "redirect:/";
    }

    @GetMapping("/todoitem/{id}/delete")
    public String deleteById(@PathVariable String id) {
        todoService.deleteById(Long.valueOf(id));

        return "redirect:/";
    }

}
