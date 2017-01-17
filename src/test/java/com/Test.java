package com;

import com.fz.mapper.StudentMapper;
import com.fz.model.Student;
import com.fz.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by webrx on 2017/1/16 0016 8:46.
 */
public class Test {
    @org.junit.Test
    public void stu(){
        SqlSession s = MyBatisUtil.getSqlSession();
        StudentMapper sm = s.getMapper(StudentMapper.class);
        //List<Student> sts = sm.search(null); 查询所有学生信息

        Map<String,Object> mm = new HashMap<String,Object>();
        //mm.put("name","李123");
        //mm.put("address","上123");
        //mm.put("id",1);

        //mm.put("name","aaa");
        //mm.put("id",2);
        //mm.put("ids",new int[]{1,3,5,6,7,8});
        mm.put("ids",new int[]{1,3,5,7});
        sm.delete(mm);
        s.commit();



        Map<String,Object> m = new HashMap<String,Object>();
        //m.put("name","%a%");
        //m.put("name","%李%");
        //m.put("name","李四");
        //m.put("address","%郑州%");
        List<Student> sts = sm.search(m);


        for(Student st : sts){
            System.out.println(st.getName());
        }
        s.commit();
        s.close();
    }
}
