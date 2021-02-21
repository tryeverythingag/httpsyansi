package com.java.pojo.admin;

import lombok.Data;

import java.util.List;

/**
 * @author ron
 * @data 2019/12/8- 15:19
 */
@Data
public class OneMenu {
    private Long oneId;
    private String oneText;
    private List<TwoMenu> twoMenuList;
}
