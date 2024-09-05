package com.ssg.service;

import com.ssg.domain.TodoVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "listController", urlPatterns = "/todo/list")
public class ListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        리스트 객체를 list.jsp 화면에 출력해주기 위해서 req에 setAttribute를 통해서 객체를 담아 전달한다
        try {
            List<TodoVO> list = TodoService.INSTANCE.selectAllList();
            req.setAttribute("dtoList", list);
            req.getRequestDispatcher("/todo/list.jsp").forward(req,resp); //원하는 위치의 페이지를 부른다. list.jsp로 이동해. 근데 req, resp를 가지고 이동해.
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
