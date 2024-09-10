package com.xc.study.util;

import com.xc.study.po.Person;

import java.lang.reflect.Field;

public class ReflectUtil {

    public static Field[] fields = Person.class.getDeclaredFields();

    public static String toCopyString(Person person) {
        StringBuilder sb = new StringBuilder();
        for (Field field : fields) {
            field.setAccessible(true); // 允许访问私有字段
            try {
                Object value = field.get(person);
                sb.append(escapeCopyField(value)).append(","); // 用制表符分隔每个字段
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        // 去掉最后一个制表符，并添加换行符
        sb.setLength(sb.length() - 1);
        sb.append("\n");
        return sb.toString();
    }


    /**
     * 为 COPY 命令转义字段中的特殊字符，如制表符、换行符等。
     */
    private static String escapeCopyField(Object value) {
        if (value == null) {
            return "\\N"; // NULL 值用 \N 表示
        }
        String stringValue = value.toString();
        // 替换特殊字符，防止干扰 COPY 格式
        return stringValue.replace("\\", "\\\\")  // 反斜杠
                .replace("\t", "\\t")  // 制表符
                .replace("\n", "\\n")  // 换行符
                .replace("\r", "\\r"); // 回车符
    }
}
