package com.dawid.app.controllers;


import com.dawid.app.services.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final TodoService todoService;

    public IndexController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping({"", "/"})
    public String getAllTodo(Model model) {
        model.addAttribute("todoitems", todoService.getTodoItems());

        return "index";
    }

}
