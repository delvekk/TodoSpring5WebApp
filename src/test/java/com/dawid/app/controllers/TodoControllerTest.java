package com.dawid.app.controllers;

import com.dawid.app.commands.TodoCommand;
import com.dawid.app.domain.Todo;
import com.dawid.app.services.TodoService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class TodoControllerTest {

    TodoController todoController;

    @Mock
    TodoService todoService;

    MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        todoController = new TodoController(todoService);

        mockMvc = MockMvcBuilders.standaloneSetup(todoController).build();
    }


    @Test
    public void getTodoItemByIdTest() throws Exception {
        Todo todo1 = new Todo();
        todo1.setId(1L);
        todo1.setTitle("Desc");

        when(todoService.findById(anyLong())).thenReturn(todo1);

        mockMvc.perform(get("/todoitem/1/show"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("todoitem"))
                .andExpect(view().name("todoitem/show"));
    }


    @Test
    public void addTodoItemPage() throws Exception{
        TodoCommand command = new TodoCommand();

        mockMvc.perform(get("/todoitem/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("todo/todoform"))
                .andExpect(model().attributeExists("todoitem"));

    }

    @Test
    public void saveTest() throws Exception{
        TodoCommand todoCommand = new TodoCommand();
        todoCommand.setId(1L);


        when(todoService.saveTodoItem(any())).thenReturn(todoCommand);

        mockMvc.perform(post("/todo")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id","")
                .param("title","something")
                .param("deadline","deadlinedate")
                .param("status","false"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/todoitem/1/show"));


    }
}