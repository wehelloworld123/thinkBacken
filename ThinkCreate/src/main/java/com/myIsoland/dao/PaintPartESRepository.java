package com.myIsoland.dao;

import com.myIsoland.enitity.product.PaintingPart;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PaintPartESRepository extends ElasticsearchRepository<PaintingPart,Long> {
}
