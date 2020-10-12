package com.myIsoland.dao;

import com.myIsoland.enitity.product.Poetry;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PoetryESRepository  extends ElasticsearchRepository<Poetry,String> {
}
