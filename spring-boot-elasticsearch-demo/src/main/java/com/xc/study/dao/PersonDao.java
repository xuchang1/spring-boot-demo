package com.xc.study.dao;

import com.xc.study.po.Person;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PersonDao extends ElasticsearchRepository<Person, String> {
}
