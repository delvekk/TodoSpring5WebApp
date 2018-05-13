package com.dawid.app.controllers;

import com.dawid.app.domain.Todo;
import com.dawid.app.repositories.TodoRepository;
import com.dawid.app.services.TodoService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {

    IndexController indexController;

    @Mock
    TodoService todoService;

    MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        indexController = new IndexController(todoService);

        mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
    }

    @Test
    public void getAllTodo() throws Exception {
        Todo todo1 = new Todo();
        todo1.setId(1L);

        Todo todo2 = new Todo();
        todo2.setId(2L);

        List<Todo> set = new ArrayList<>();
        set.add(todo1);
        set.add(todo2);

        when(todoService.getTodoItems()).thenReturn(set);


        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("todoitem"));

    }
}