package com.xc.study.service.impl;

import com.xc.study.dao.PersonDao;
import com.xc.study.po.KeyCount;
import com.xc.study.po.Person;
import com.xc.study.service.HelloService;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class HelloServiceImpl implements HelloService {
    @Resource
    private PersonDao personDao;

    @Resource
    private ElasticsearchOperations elasticsearchOperations;

    @Override
    public Person say(String id) {
        return personDao.findById(id).orElse(null);
    }

    @Override
    public List<KeyCount> say1() {
        List<KeyCount> result = new ArrayList<>();
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .addAggregation(AggregationBuilders.terms("sexAggs").field("sex"))
                .build();
        query.setMaxResults(0);
        SearchHits<Person> search = elasticsearchOperations.search(query, Person.class);
        Terms sexAggs = search.getAggregations().get("sexAggs");
        sexAggs.getBuckets().forEach(x -> {
            KeyCount keyCount = new KeyCount();
            keyCount.setKey(String.valueOf(x.getKey()));
            keyCount.setCount(x.getDocCount());
            result.add(keyCount);
        });
        return result;
    }
}
