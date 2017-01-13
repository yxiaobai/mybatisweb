package com.fz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * Created by webrx on 2017/1/12 0012 8:54.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Member {
    private int id;
    private String account;
    private String pass;
    private String name;
    private Address address;
    private List<Order> orders;
}
