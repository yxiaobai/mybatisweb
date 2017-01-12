package com.fz.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by webrx on 2017/1/10 0010 8:46.
 */
public class MyBatisUtil {
    private final static SqlSessionFactory sf;
    private final static SqlSession session;

    static {
        String resource = "conf.xml";
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader(resource);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        sf = new SqlSessionFactoryBuilder().build(reader);
        session = sf.openSession();
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sf;
    }

    public static SqlSession getSqlSession() {
        return session;
    }
}
