package org.zerock.jdbcex.todo;

import org.zerock.jdbcex.dto.TodoDTO;
import org.zerock.jdbcex.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "todoReadController", urlPatterns = "/todo/read")
public class TodoReadController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println(" url : " + "/todo/read");

        Long tno = Long.valueOf(req.getParameter("tno"));
        TodoDTO todoDTO =TodoService.INSTANCE.get(tno);
        req.setAttribute("todoDTO", todoDTO);
        req.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(req,resp);
    }
}