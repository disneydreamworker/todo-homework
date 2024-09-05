package com.ssg.dao;

import com.ssg.domain.TodoVO;
import com.ssg.service.TodoService;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TodoDAO {
    public void insert(TodoVO vo) throws Exception {
        String sql = "insert into tbl_todo (tno,title,dueDate,finished) values(null,?,?,?)";

        @Cleanup Connection connection = ConnectionUtill.INSTANCE.getConnection();
        @Cleanup PreparedStatement psmt = connection.prepareStatement(sql);

        psmt.setString(1, vo.getTitle());
        psmt.setDate(2, Date.valueOf(vo.getDueDate()));
        psmt.setBoolean(3, vo.isFinished());
        psmt.executeUpdate();
    }

    public List<TodoVO> selectAllList() throws Exception {
        //10개의 글을 만들어서 글 전체의 리스트 객체를 반환함
        String sql = "select * from tbl_todo";
        @Cleanup Connection connection = ConnectionUtill.INSTANCE.getConnection();
        @Cleanup PreparedStatement psmt = connection.prepareStatement(sql);
        @Cleanup ResultSet rs = psmt.executeQuery();

        List<TodoVO> todoVOS = new ArrayList<>();
        while(rs.next()) {
            TodoVO vo = TodoVO.builder().tno(rs.getLong("tno")).title(rs.getString("title")).dueDate(rs.getDate("dueDate").toLocalDate()).finished(rs.getBoolean("finished")).build();
            todoVOS.add(vo);
        }
        return todoVOS;
    }

    private TodoVO selectOne(long tno) throws Exception {
        //10개의 글을 만들어서 글 전체의 리스트 객체를 반환함
        String sql = "select * from tbl_todo where tno = ?";
        @Cleanup Connection connection = ConnectionUtill.INSTANCE.getConnection();
        @Cleanup PreparedStatement psmt = connection.prepareStatement(sql);
        psmt.setLong(1, tno);
        @Cleanup ResultSet rs = psmt.executeQuery();

        rs.next();
        TodoVO todoVO = TodoVO.builder()
                .tno(rs.getLong("tno"))
                .title(rs.getString("title"))
                .dueDate(rs.getDate("dueDate").toLocalDate())
                .finished(rs.getBoolean("finished")).build();
        return todoVO;
    }

    private void deleteOne(Long tno) throws Exception{
        String sql = "delete from tbl_todo where tno = ?";
        @Cleanup Connection connection = ConnectionUtill.INSTANCE.getConnection();
        @Cleanup PreparedStatement psmt = connection.prepareStatement(sql);
        psmt.setLong(1, tno);
        psmt.executeUpdate();
    }

    private void updateOne(TodoVO vo) throws Exception{
        String sql = "update tbl_todo set title = ?, dueDate = ?, finished = ? where tno = ?";
        @Cleanup Connection connection = ConnectionUtill.INSTANCE.getConnection();
        @Cleanup PreparedStatement psmt = connection.prepareStatement(sql);

        psmt.setString(1, vo.getTitle());
        psmt.setDate(2, Date.valueOf(vo.getDueDate()));
        psmt.setBoolean(3, vo.isFinished());
        psmt.setLong(4, vo.getTno());

        psmt.executeUpdate();
    }
}
