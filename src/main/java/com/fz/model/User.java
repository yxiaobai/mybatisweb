package com.fz.model;
import lombok.Data;

import java.util.List;
/**
 * Created by webrx on 2017/1/13 0013 10:31.
 */
@Data
public class User {
    private int id;
    private String name;
    private String pass;
    private List<Role> roles;
}
