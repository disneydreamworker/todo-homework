package com.ssg.service;

import com.ssg.domain.TodoVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "removeController", urlPatterns = "/todo/remove")
public class RemoveController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long tno = Long.parseLong(req.getParameter("tno"));
        try {
            TodoService.INSTANCE.deleteOne(tno);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect("/todo/list");
    }
}
