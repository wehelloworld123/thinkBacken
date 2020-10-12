package com.myIsoland.dao;

import com.myIsoland.enitity.product.PoemSet;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PoetrySetESRepository extends ElasticsearchRepository<PoemSet,Long> {
}
