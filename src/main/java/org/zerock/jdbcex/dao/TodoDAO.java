package org.zerock.jdbcex.dao;

import lombok.Cleanup;
import org.zerock.jdbcex.domain.TodoVO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TodoDAO {


    public String getTime() {
        String now = null;

        //try with Resource 문
//        try (Connection connection = ConnectionUtil.INSTANCE.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement("select now()");
//             ResultSet rs = preparedStatement.executeQuery();
//        ) {
//            rs.next();
//            now = rs.getString(1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        try {
            Connection conn = ConnectionUtil.INSTANCE.getConnection();
            PreparedStatement psmt = conn.prepareStatement("select now()");
            ResultSet rs = psmt.executeQuery();
            rs.next();
            now = rs.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //conn.close(); 리소스 공유하므로 항상 닫아야 함, 하지만  try with resource 문을 사용하면
        //닫아 줄 필요 없음

        return now;
    }

//    public String getTime() throws Exception {
//        String now = null;
//
//        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
//        @Cleanup PreparedStatement psmt = conn.prepareStatement("select now()");
//        @Cleanup ResultSet rs = psmt.executeQuery();
//        rs.next();
//        now = rs.getString(1);
//        return now;
//    }

    public void insert(TodoVO todoVO) throws Exception {
        String sql = "insert into tbl_todo (title, dueDate, finished) values (?, ?, ?)";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, todoVO.getTitle());
        preparedStatement.setDate(2, Date.valueOf(todoVO.getDueDate()));
        preparedStatement.setBoolean(3, todoVO.isFinished());

        preparedStatement.executeUpdate();

    }

    public List<TodoVO> selectAll() throws Exception {
        String sql = "select * from tbl_todo";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        List<TodoVO> list = new ArrayList<>();

        while (resultSet.next()) {
            TodoVO vo = TodoVO.builder()
                    .tno(resultSet.getLong("tno"))
                    .title(resultSet.getString("title"))
                    .dueDate(resultSet.getDate("dueDate").toLocalDate())
                    .finished(resultSet.getBoolean("finished"))
                    .build();
            list.add(vo);
        }
        return list;
    }

    //    글 하나 검색
    public TodoVO selectOne(Long tno) throws Exception {

        String sql = "select * from tbl_todo where tno = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setLong(1, tno);

        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();
        TodoVO vo = TodoVO.builder()
                .tno(resultSet.getLong("tno"))
                .title(resultSet.getString("title"))
                .dueDate(resultSet.getDate("dueDate").toLocalDate())
                .finished(resultSet.getBoolean("finished"))
                .build();

        return vo;
    }

    public void deleteOne(Long tno) throws Exception {

        String sql = "delete from tbl_todo where tno = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setLong(1, tno);

        preparedStatement.executeUpdate();
    }

    public void updateOne(TodoVO todoVO) throws Exception {

        String sql = "update tbl_todo set title =?, dueDate = ?, finished = ? where tno =?";

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement psmt = conn.prepareStatement(sql);

        psmt.setString(1, todoVO.getTitle());
        psmt.setDate(2, Date.valueOf(todoVO.getDueDate()));
        psmt.setBoolean(3, todoVO.isFinished());
        psmt.setLong(4, todoVO.getTno());

        psmt.executeUpdate();
    }

}