package com.fz.mapper;

import com.fz.model.Member;

import java.util.List;
import java.util.Map;

/**
 * Created by webrx on 2017/1/12 0012 8:56.
 */
public interface MemberMapper {
    public Member queryById(int id);

    public Member findById(int id);

    public List<Map<String,Object>> query();
    public List<Map<String,Object>> queryAll();
}
