package com;

import com.fz.mybatis.MyBatisUtil;
import com.mybatis.mapper.StudentMapper;
import com.mybatis.model.Student;
import org.apache.ibatis.session.SqlSession;

/**
 * Created by webrx on 2017/1/17 0017 9:17.
 */
public class My {
    @org.junit.Test
    public void gen(){
        SqlSession s = MyBatisUtil.getSqlSession();
        StudentMapper sm = s.getMapper(StudentMapper.class);
        Student st = new Student();
        st.setName("jack");
        st.setAddress("郑州");
        sm.insert(st);
        s.commit();
        s.close();
    }
}
