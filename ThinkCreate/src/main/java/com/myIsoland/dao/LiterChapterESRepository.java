package com.myIsoland.dao;

import com.myIsoland.enitity.product.LiterCharpt;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface LiterChapterESRepository extends ElasticsearchRepository<LiterCharpt,Long> {
}
