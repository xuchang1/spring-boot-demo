package com.xc.study.service;

import com.xc.study.po.Person;

public interface HelloService {

    /**
     * 根据id，查询用户信息
     *
     * @param id id
     * @return 用户信息
     */
    Person queryPersonById(Integer id);
}
