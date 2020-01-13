package com.storyxc.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xc
 * @description
 * @createdTime 2020/1/12 21:02
 */
@Data
@EqualsAndHashCode
public class Image {
    private Integer id;
    private String startdate;
    private String fullstartdate;
    private String enddate;
    private String url;
    private String copyright;
    private String copyrightlink;
    private String title;
    private String quiz;
    private String wp;
    private String hsh;
}
