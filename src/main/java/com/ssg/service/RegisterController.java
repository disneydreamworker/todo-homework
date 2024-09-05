package com.ssg.service;

import com.ssg.dao.TodoDAO;
import com.ssg.domain.TodoVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "registerController", urlPatterns = "/todo/register")
public class RegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("register.jsp GET");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/todo/register.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, RuntimeException {
        //입력받은 파라미터로 VO 객체 만들기
        TodoVO todoVO = TodoVO.builder()
                        .title(req.getParameter("title"))
                        .dueDate(Date.valueOf(req.getParameter("dueDate")).toLocalDate())
                        .build();

        //VO 객체를 DAO 메서드를 통해 디비에 입력
        try {
            TodoService.INSTANCE.insert(todoVO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        resp.sendRedirect("/todo/list");
    }
}
