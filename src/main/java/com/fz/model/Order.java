package com.fz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by webrx on 2017/1/13 0013 8:45.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
    private int id;
    private String no;
    private double money;
}
