package com.myIsoland.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

public class LiterContentModel {

    private Integer id;

    private String content;

    private String detail;

    private String file;

    private Integer charpId;

    private String bookId;

    private int likes;

    private int recomNo;

    private int view;//是否展示

    private int adopt;

    private String creator;

    private String createAvat;

    private String bookName;

    private String charpName;

}
