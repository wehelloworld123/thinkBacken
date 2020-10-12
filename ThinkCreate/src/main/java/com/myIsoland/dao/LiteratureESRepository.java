package com.myIsoland.dao;

import com.myIsoland.enitity.product.Literature;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface LiteratureESRepository extends ElasticsearchRepository<Literature,String> {
}
