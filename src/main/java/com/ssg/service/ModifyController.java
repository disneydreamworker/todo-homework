package com.ssg.service;

import com.ssg.domain.TodoVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "modifyController", urlPatterns = "/todo/modify")
public class ModifyController extends HttpServlet {
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

        RequestDispatcher dispatcher = req.getRequestDispatcher("/todo/modify.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TodoVO todoVO = TodoVO.builder()
                .tno(Long.parseLong(req.getParameter("tno")))
                .title(req.getParameter("title"))
                .dueDate(Date.valueOf(req.getParameter("dueDate")).toLocalDate())
                .finished(Boolean.valueOf(req.getParameter("finished")))
                .build();

        try {
            TodoService.INSTANCE.updateOne(todoVO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        resp.sendRedirect("/todo/list");
    }
}
