package com.fz.mapper;

import com.fz.model.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by webrx on 2017/1/10 0010 8:47.
 */
public interface StudentMapper{

    public int update(Map<String,Object> map);

    public int delete(Map<String,Object> m);

    public List<Student> search(Map<String,Object> map);

    public List<Student> query();

    @Select("select name from student")
    public List<String> queryNames();

    @Select("select address,name from student where address like #{address}")
    public List<Map<String,Object>> queryName(String address);

    @Select("select count(*) from student")
    public int count();

    @Delete("delete from student where id=#{id}")
    public int deleteById(int id);
    public int save(Student student);
    public int insert(String name,String address);
}
