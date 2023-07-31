package org.zerock.jdbcex.todo;

import lombok.extern.log4j.Log4j2;
import org.zerock.jdbcex.dto.TodoDTO;
import org.zerock.jdbcex.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Log4j2
@WebServlet(name = "todoListController", urlPatterns = "/todo/list")
public class TodoListController extends HttpServlet {
    private TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info(" url : " + "/todo/list");

//        List<TodoDTO> dtoList=  TodoService.INSTANCE.getList();


        try {
            List<TodoDTO> dtoList = todoService.listAll();

            request.setAttribute("dtoList", dtoList);

            request.getRequestDispatcher("/WEB-INF/todo/list.jsp").forward(request, response);
        } catch (Exception e) {
            log.error(e.getMessage());
        }


    }
}