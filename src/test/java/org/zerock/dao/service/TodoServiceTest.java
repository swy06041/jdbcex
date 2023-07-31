package org.zerock.dao.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zerock.jdbcex.dto.TodoDTO;
import org.zerock.jdbcex.service.TodoService;

import java.time.LocalDate;

public class TodoServiceTest {


    private TodoService todoService;


    @BeforeEach
    public void ready(){
        todoService=TodoService.INSTANCE;
    }



    @Test
    public void testRegister() throws  Exception{
        TodoDTO todoDTO = TodoDTO.builder().title("JDBD Test2").dueDate(LocalDate.now()).build();
        todoService.register(todoDTO);
    }

}