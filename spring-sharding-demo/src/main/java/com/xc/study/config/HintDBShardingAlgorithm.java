package com.xc.study.config;



import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingValue;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

/**
 * hint策略分库
 */
@Component
public class HintDBShardingAlgorithm implements HintShardingAlgorithm<Integer> {
    /**
     *
     * @param databBaseNames 数据库集合
     * @param hintShardingValue 分片键的值
     * @return 真实访问数据库
     */
    @Override
    public Collection<String> doSharding(Collection<String> databBaseNames, HintShardingValue<Integer> hintShardingValue) {
        Collection<String> result = new ArrayList<>();
//        for (String dataBaseName : databBaseNames) {
//            for (Integer value : hintShardingValue.getValues()) {
//                if (dataBaseName.endsWith(String.valueOf(value % 2))) {
//                    result.add(dataBaseName);
//                }
//            }
//        }
//        result.add("ds01");
        result.add("ds00");
        return result;
    }

    @Override
    public Properties getProps() {
        return null;
    }

    @Override
    public void init(Properties properties) {

    }

    @Override
    public String getType() {
        return "HintDBShardingAlgorithm";
    }
}
