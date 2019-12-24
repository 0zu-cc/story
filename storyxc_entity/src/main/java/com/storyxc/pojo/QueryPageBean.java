package com.storyxc.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class QueryPageBean implements Serializable {
    private Integer currentPage;
    private Integer pageSize;
    private String queryString;

}