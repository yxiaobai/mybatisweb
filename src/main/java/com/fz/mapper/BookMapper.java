package com.fz.mapper;

import com.fz.model.Book;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;
import java.util.Map;

/**
 * Created by webrx on 2017/1/10 0010 11:07.
 */
public interface BookMapper {
    public int save(Book book);
    public List<Book> query();
    public List<Book> show();

    @Select("call myshow") @Options(statementType = StatementType.CALLABLE) @ResultMap("mybook")
    public List<Book> ss();

    @Insert("call mysave(#{name},#{price})") @Options(statementType = StatementType.CALLABLE)
    public int mysave( @Param("name") String n,@Param("price") double d);


    public int mydel(int id);

    public int myupdate(int id,String name,double price);

    public Map<String,Integer> getCount(Map<String,Integer> m);

    public List<Book> page(int start,int pagesize);
}
