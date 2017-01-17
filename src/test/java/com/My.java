package com;

import com.fz.mybatis.MyBatisUtil;
import com.mybatis.mapper.StudentMapper;
import com.mybatis.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * Created by webrx on 2017/1/17 0017 9:17.
 */
@Slf4j
public class My {


    @org.junit.Test
    public void gen(){
        SqlSessionFactory sf = MyBatisUtil.getSqlSessionFactory();
        SqlSession s = sf.openSession();
        StudentMapper sm = s.getMapper(StudentMapper.class);
        //Student st = new Student();
        //st.setName("jack");
        //st.setAddress("郑州");
        //sm.insert(st);
        List<Student> a = sm.queryAll();
        s.close();
        //s.clearCache();
        SqlSession ss = sf.openSession();
        System.out.println(ss==s);
        StudentMapper ssm = ss.getMapper(StudentMapper.class);
        List<Student> b = ssm.queryAll();
        System.out.println(a==b);
        //s.clearCache();


        ss.close();
    }
}
