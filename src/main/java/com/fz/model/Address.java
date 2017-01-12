package com.fz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by webrx on 2017/1/12 0012 8:55.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Address {
    private int id;
    private String name;
    private String phone;
}
