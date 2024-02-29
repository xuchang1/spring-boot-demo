package com.xc.study.config;


import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingValue;

import java.util.ArrayList;
import java.util.Collection;

/**
 *  hint分表策略
 */
//public class HintTableShardingAlgorithm implements HintShardingAlgorithm<Integer> {
//
//    /**
//     * hint分表策略
//     * @param tableNames 表名集合
//     * @param hintShardingValue 分片键的值
//     * @return 真实访问表
//     */
//    @Override
//    public Collection<String> doSharding(Collection<String> tableNames, HintShardingValue<Integer> hintShardingValue) {
//        Collection<String> result = new ArrayList<>();
////        for (String tableName : tableNames) {
////            for (Integer value : hintShardingValue.getValues()) {
////                if (tableName.endsWith(String.valueOf(value % 3))) {
////                    result.add(tableName);
////                }
////            }
////        }
//        result.add("ds02");
//        return result;
//    }
//}
