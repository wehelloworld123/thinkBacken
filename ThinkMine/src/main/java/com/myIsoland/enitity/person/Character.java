package com.myIsoland.enitity.person;

import com.myIsoland.common.base.BaseEntity;

import java.io.Serializable;

public class Character extends BaseEntity implements Serializable {

    private Integer id;

    private String charact;//九型人格

    private int rank;//级别

    private String relate;//关联人格

    private String potential;//潜在人格

    private String feature;//表现
}
