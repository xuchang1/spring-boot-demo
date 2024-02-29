package com.xc.study.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Test {
    public static void main(String[] args) {
        BigDecimal value1 = new BigDecimal("2.245");
        BigDecimal value2 = new BigDecimal("2.244");
        BigDecimal value3 = new BigDecimal("2.246");

        // 使用半上舍入方式，将小数部分舍入到最接近的整数
        System.out.println(value1.setScale(2, RoundingMode.HALF_UP));
        System.out.println(value2.setScale(2, RoundingMode.HALF_UP));
        System.out.println(value3.setScale(2, RoundingMode.HALF_UP));

    }
}
