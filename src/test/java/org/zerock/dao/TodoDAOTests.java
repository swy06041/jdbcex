package org.zerock.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zerock.jdbcex.dao.TodoDAO;
import org.zerock.jdbcex.domain.TodoVO;

import java.time.LocalDate;
import java.util.List;

public class TodoDAOTests {
    private TodoDAO todoDAO;


    @BeforeEach
    public void ready() {
        todoDAO = new TodoDAO();
    }


    @Test
    public void testTime() throws Exception {
        System.out.println("커넥션 풀 객체가 sql 쿼리를 돌린 후 시간을 가져온 결과 " + todoDAO.getTime());

    }

    @Test
    public void testInsert() throws Exception {
        TodoVO todoVO = TodoVO.builder()
                .title("Test1")
                .dueDate(LocalDate.of(2021, 12, 31))
                .build();
        System.out.println("todoVO객체~");
        todoDAO.insert(todoVO);
    }

    @Test
    public void testList() throws Exception {
        List<TodoVO> list = todoDAO.selectAll();
        list.forEach(vo -> System.out.println(vo));
    }

    @Test
    public void testSelectOne() throws Exception {

        Long tno = 1L; //반드시 존재하는 번호를 이용

        TodoVO vo = todoDAO.selectOne(tno);

        System.out.println(vo);
    }

    @Test
    public void testUpdateOne() throws Exception {
        TodoVO todoVO = TodoVO.builder()
                .tno(1L)
                .title("Sample Title")
                .dueDate(LocalDate.of(2023, 7, 26))
                .finished(true)
                .build();

        todoDAO.updateOne(todoVO);

    }
}