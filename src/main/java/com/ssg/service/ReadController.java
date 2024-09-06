package com.ssg.service;

import com.ssg.domain.TodoVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "readController", urlPatterns = "/todo/read")
public class ReadController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long tno = Long.parseLong(req.getParameter("tno"));
        TodoVO vo = null;
        try {
            vo = TodoService.INSTANCE.selectOne(tno);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("dto", vo); //데이터 담기

        RequestDispatcher dispatcher = req.getRequestDispatcher("/todo/read.jsp");
        dispatcher.forward(req,resp);
    }
}
