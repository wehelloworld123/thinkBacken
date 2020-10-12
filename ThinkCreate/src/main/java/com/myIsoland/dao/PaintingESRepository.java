package com.myIsoland.dao;

import com.myIsoland.enitity.product.Painting;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PaintingESRepository extends ElasticsearchRepository<Painting,String> {
}
