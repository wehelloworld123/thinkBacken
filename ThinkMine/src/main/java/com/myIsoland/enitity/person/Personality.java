package com.myIsoland.enitity.person;

import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_per_personality")
public class Personality extends BaseEntity implements Serializable {

    private Integer id;

    private String charact;//九型人格

    private int rank;//级别

    private String relate;//关联人格

    private String potential;//潜在人格

    private String feature;//表现

    public Personality() {
        super();
    }

    public Personality(String charact, int rank, String relate, String potential, String feature) {
        this.charact = charact;
        this.rank = rank;
        this.relate = relate;
        this.potential = potential;
        this.feature = feature;
    }
}
