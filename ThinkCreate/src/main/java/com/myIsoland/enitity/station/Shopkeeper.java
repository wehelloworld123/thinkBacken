package com.myIsoland.enitity.station;

import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

@Data
public class Shopkeeper extends BaseEntity {

    private String keepNo;

    private String shopNo;

    private String name;

    private String logo;

    private String describe;

}
