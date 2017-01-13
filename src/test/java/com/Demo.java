package com;

import com.fz.mapper.BookMapper;
import com.fz.mapper.MemberMapper;
import com.fz.mapper.RoleMapper;
import com.fz.mapper.StudentMapper;
import com.fz.model.Book;
import com.fz.model.Member;
import com.fz.model.Role;
import com.fz.model.Student;
import com.fz.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * Created by webrx on 2017/1/10 0010 8:44.
 */
public class Demo {

    @Test
    public void one2many(){
        SqlSession s = MyBatisUtil.getSqlSession();
        //UserMapper um = s.getMapper(UserMapper.class);
        //System.out.println(um.findById(1));

        RoleMapper rm = s.getMapper(RoleMapper.class);
        Role r = rm.findById(2);
        System.out.println(r.getName());
        System.out.println(r.getUser());
        s.commit();
        s.close();
    }


    @Test
    public void oneone(){
        SqlSession s = MyBatisUtil.getSqlSession();
        MemberMapper mm = s.getMapper(MemberMapper.class);
        Member m1 = mm.findById(1);
        Member m2 = mm.findById(2);
        Member m3 = mm.findById(3);
        System.out.println(m1);
        System.out.println(m2);
        System.out.println(m3);

        //AddressMapper am = s.getMapper(AddressMapper.class);
        //System.out.println(am.queryById(100));


      /*  List<Map<String,Object>> ls = mm.query();
        System.out.println(ls.size());
        for(Map<String,Object> m : ls){
            System.out.println(m.get("name"));
            System.out.println(m.get("aname"));
        }*/
        s.commit();
        s.close();
    }
    @Test
    public void mybook(){
        SqlSession s = MyBatisUtil.getSqlSession();
        BookMapper bm = s.getMapper(BookMapper.class);
        //bm.mysave("mysql",120);
        //bm.mydel(4);
        //bm.myupdate(6,"mysql8.0",90);
       // Map<String,Integer> mm = new HashMap<String, Integer>();
       // mm.put("myc",0);
        //bm.getCount(mm);
        //System.out.println(mm.get("myc"));

        List<Book> bs = bm.page(0,2);
        System.out.println(bs);
        System.out.println(bs.size());

        s.commit();
        s.close();
    }



    @Test
    public void book(){
        SqlSession s = MyBatisUtil.getSqlSession();
//        Book b = new Book();
//        b.setPrice(30);
//        b.setName("php项目开发");
        BookMapper bm = s.getMapper(BookMapper.class);
//        bm.save(b);

        //List<Book> bks =bm.query();
        List<Book> bks = bm.ss();
//        System.out.println(bks.size());
        for(Book b : bks){
            System.out.println(b.getName());
        }
        s.commit();
        s.close();
    }



    @Test
    public void ok(){
        SqlSession s = MyBatisUtil.getSqlSession();
        StudentMapper sm = s.getMapper(StudentMapper.class);
        //System.out.println(sm.deleteById(15));

        Student stu = new Student();
        stu.setName("andy");
        stu.setAddress("上海");
        System.out.println(sm.save(stu));
        System.out.println(sm.insert("jack","北京"));
        System.out.println("--------------");
       //List<Student> sss = sm.query();
       //for(Student st : sss){
       //    System.out.println(st.getName());
       //}

        //List<String> ss = sm.queryNames();
        //System.out.println(ss.size());
        //for(String sn : ss){
        //    System.out.println(sn);
        //}

        //List<Map<String,Object>> ms = sm.queryName("%上海%");
        //for(Map<String,Object> m : ms){
        //    System.out.println(m.get("name"));
        //    System.out.println(m.get("address"));
        //}

        System.out.println(sm.count());

        s.commit();
        s.close();
    }
}
