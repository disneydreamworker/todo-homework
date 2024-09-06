package com.ssg.service;

import com.ssg.dao.TodoDAO;
import com.ssg.domain.TodoVO;

import java.util.List;

public enum TodoService {
    INSTANCE;

    TodoDAO dao = new TodoDAO();

    public void insert(TodoVO vo) throws Exception {
        dao.insert(vo);
    }

    public List<TodoVO> selectAllList() throws Exception {
        return dao.selectAllList();
    }


    public TodoVO selectOne(long tno) throws Exception {
        return dao.selectOne(tno);
    }

    public void deleteOne(Long tno) throws Exception {
        dao.deleteOne(tno);
    }

    public void updateOne(TodoVO vo) throws Exception {
        dao.updateOne(vo);
    }
}
