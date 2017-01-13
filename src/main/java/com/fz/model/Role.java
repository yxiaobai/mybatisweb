package com.fz.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by webrx on 2017/1/13 0013 10:32.
 */
//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private int id;
    private String name;
    private User user;
}
