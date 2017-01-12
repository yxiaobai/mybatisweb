package com.fz.servlet;

import com.fz.mapper.StudentMapper;
import com.fz.model.Student;
import com.fz.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by webrx on 2017/1/10 0010 9:11.
 */
@WebServlet("/show.action")
public class Show extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession s = MyBatisUtil.getSqlSession();
        List<Student> st = s.getMapper(StudentMapper.class).query();
        req.setAttribute("st", st);
        req.getRequestDispatcher("show.jsp").forward(req,resp);

    }
}
