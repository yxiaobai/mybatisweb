package com.fz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by webrx on 2017/1/10 0010 11:06.
 */
@Data @AllArgsConstructor @NoArgsConstructor
public class Book {
    private int id;
    private String name;
    private double price;
}
