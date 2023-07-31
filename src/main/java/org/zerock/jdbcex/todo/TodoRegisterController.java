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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Log4j2
@WebServlet(name = "todoRegisterController", value = "/todo/register")
public class TodoRegisterController extends HttpServlet {

    private final TodoService todoService = TodoService.INSTANCE;
    private final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info("/todo/register GET .......");
        request.getRequestDispatcher("/WEB-INF/todo/register.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        TodoDTO todoDTO = TodoDTO.builder()
                .title(request.getParameter("title"))
                .dueDate(LocalDate.parse(request.getParameter("dueDate"),DATEFORMATTER ))
                .build();

        log.info("/todo/register POST 방식으로 요청");
        log.info("해야할 일을 입력해서 서버에 올릴때 전홍할 디티오객체" + todoDTO);

        try {
            todoService.register(todoDTO);
        } catch (Exception e) {
            log.error("post 방식으로 todo/regist시 에러남");
            e.printStackTrace();
        }
        response.sendRedirect("/todo/list");

    }
}