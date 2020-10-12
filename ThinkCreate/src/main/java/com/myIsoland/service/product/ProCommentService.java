package com.myIsoland.service.product;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.product.ProComment;
import com.myIsoland.enums.RecomType;
import com.myIsoland.model.ResultSet;

import java.util.Date;

public interface ProCommentService  extends IService<ProComment> {

    ResultSet<ProComment> GetProCommentsByDate(String creationId, RecomType type, Date date, int start, int limit);
}
