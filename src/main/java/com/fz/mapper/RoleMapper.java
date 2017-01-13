package com.fz.mapper;


import com.fz.model.Role;

/**
 * Created by webrx on 2017/1/12 0012 10:29.
 */
public interface RoleMapper {
    public Role findByUserId(int id);
    public Role findById(int id);
}
