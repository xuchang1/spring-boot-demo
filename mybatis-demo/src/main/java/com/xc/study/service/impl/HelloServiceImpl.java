package com.xc.study.service.impl;

import com.xc.study.mapper.PersonMapper;
import com.xc.study.po.Person;
import com.xc.study.service.HelloService;
import com.xc.study.util.ReflectUtil;
import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class HelloServiceImpl implements HelloService {

    @Resource
    private PersonMapper personMapper;

    @Override
    public Person queryPersonById(Integer id) {
        return personMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteById(Integer id) {
        return personMapper.deleteByPrimaryKey(id);
    }

    @Autowired
    private DataSource dataSource;

    @Override
    public long batchSave(Integer size, Integer batchSize) {
        long startTime = System.currentTimeMillis();
        System.out.println("--------startTime"  + startTime);
        String sql = "COPY person (id, name, age, sex, address,name0,name1,name2,name3,name4,name5,name6,name7,name8,name9,name10,name11,name12,name13,name14,name15,name16,name17,name18,name19,name20,name21,name22,name23,name24,name25,name26,name27,name28,name29,name30,name31,name32,name33,name34,name35,name36,name37,name38,name39,name40,name41,name42,name43,name44,name45,name46,name47,name48,name49,name50,name51,name52,name53,name54,name55,name56,name57,name58,name59,name60,name61,name62,name63,name64,name65,name66,name67,name68,name69,name70,name71,name72,name73,name74,name75,name76,name77,name78,name79,name80,name81,name82,name83,name84,name85,name86,name87,name88,name89,name90,name91,name92,name93,name94,name95,name96,name97,name98,name99) FROM STDIN WITH (FORMAT csv, DELIMITER ',')";
        try {
            Connection connection  = dataSource.getConnection();
            connection.setAutoCommit(false); // 开启事务
            CopyManager copyManager = new CopyManager(connection.unwrap(BaseConnection.class));
            StringBuilder sb = new StringBuilder();
            long tim1 = System.currentTimeMillis();
            System.out.println("--------tim1"  + tim1);
            for (int i = 1; i <= size; i++) {
                Person person = data1(i);
                sb.append(ReflectUtil.toCopyString(person));
                if (i % batchSize == 0) {
                    copyManager.copyIn(sql, new StringReader(sb.toString()));
                    sb = new StringBuilder();
                    System.out.println("--------tim2"  + (System.currentTimeMillis() - tim1));
                    tim1 = System.currentTimeMillis();
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return System.currentTimeMillis() - startTime;
    }

    private static Person data1(int i) {
        Person person = new Person();
        person.setId(i);
        person.setAge(i);
        person.setName("小明" + i);
        person.setSex(1);
        person.setAddress("地址" + i);
        person.setName0("小明111111111111111111111111111111");
        person.setName1("小明111111111111111111111111111111");
        person.setName2("小明111111111111111111111111111111");
        person.setName3("小明111111111111111111111111111111");
        person.setName4("小明111111111111111111111111111111");
        person.setName5("小明111111111111111111111111111111");
        person.setName6("小明111111111111111111111111111111");
        person.setName7("小明111111111111111111111111111111");
        person.setName8("小明111111111111111111111111111111");
        person.setName9("小明111111111111111111111111111111");
        person.setName10("小明111111111111111111111111111111");
        person.setName11("小明111111111111111111111111111111");
        person.setName12("小明111111111111111111111111111111");
        person.setName13("小明111111111111111111111111111111");
        person.setName14("小明111111111111111111111111111111");
        person.setName15("小明111111111111111111111111111111");
        person.setName16("小明111111111111111111111111111111");
        person.setName17("小明111111111111111111111111111111");
        person.setName18("小明111111111111111111111111111111");
        person.setName19("小明111111111111111111111111111111");
        person.setName20("小明111111111111111111111111111111");
        person.setName21("小明111111111111111111111111111111");
        person.setName22("小明111111111111111111111111111111");
        person.setName23("小明111111111111111111111111111111");
        person.setName24("小明111111111111111111111111111111");
        person.setName25("小明111111111111111111111111111111");
        person.setName26("小明111111111111111111111111111111");
        person.setName27("小明111111111111111111111111111111");
        person.setName28("小明111111111111111111111111111111");
        person.setName29("小明111111111111111111111111111111");
        person.setName30("小明111111111111111111111111111111");
        person.setName31("小明111111111111111111111111111111");
        person.setName32("小明111111111111111111111111111111");
        person.setName33("小明111111111111111111111111111111");
        person.setName34("小明111111111111111111111111111111");
        person.setName35("小明111111111111111111111111111111");
        person.setName36("小明111111111111111111111111111111");
        person.setName37("小明111111111111111111111111111111");
        person.setName38("小明111111111111111111111111111111");
        person.setName39("小明111111111111111111111111111111");
        person.setName40("小明111111111111111111111111111111");
        person.setName41("小明111111111111111111111111111111");
        person.setName42("小明111111111111111111111111111111");
        person.setName43("小明111111111111111111111111111111");
        person.setName44("小明111111111111111111111111111111");
        person.setName45("小明111111111111111111111111111111");
        person.setName46("小明111111111111111111111111111111");
        person.setName47("小明111111111111111111111111111111");
        person.setName48("小明111111111111111111111111111111");
        person.setName49("小明111111111111111111111111111111");
        person.setName50("小明111111111111111111111111111111");
        person.setName51("小明111111111111111111111111111111");
        person.setName52("小明111111111111111111111111111111");
        person.setName53("小明111111111111111111111111111111");
        person.setName54("小明111111111111111111111111111111");
        person.setName55("小明111111111111111111111111111111");
        person.setName56("小明111111111111111111111111111111");
        person.setName57("小明111111111111111111111111111111");
        person.setName58("小明111111111111111111111111111111");
        person.setName59("小明111111111111111111111111111111");
        person.setName60("小明111111111111111111111111111111");
        person.setName61("小明111111111111111111111111111111");
        person.setName62("小明111111111111111111111111111111");
        person.setName63("小明111111111111111111111111111111");
        person.setName64("小明111111111111111111111111111111");
        person.setName65("小明111111111111111111111111111111");
        person.setName66("小明111111111111111111111111111111");
        person.setName67("小明111111111111111111111111111111");
        person.setName68("小明111111111111111111111111111111");
        person.setName69("小明111111111111111111111111111111");
        person.setName70("小明111111111111111111111111111111");
        person.setName71("小明111111111111111111111111111111");
        person.setName72("小明111111111111111111111111111111");
        person.setName73("小明111111111111111111111111111111");
        person.setName74("小明111111111111111111111111111111");
        person.setName75("小明111111111111111111111111111111");
        person.setName76("小明111111111111111111111111111111");
        person.setName77("小明111111111111111111111111111111");
        person.setName78("小明111111111111111111111111111111");
        person.setName79("小明111111111111111111111111111111");
        person.setName80("小明111111111111111111111111111111");
        person.setName81("小明111111111111111111111111111111");
        person.setName82("小明111111111111111111111111111111");
        person.setName83("小明111111111111111111111111111111");
        person.setName84("小明111111111111111111111111111111");
        person.setName85("小明111111111111111111111111111111");
        person.setName86("小明111111111111111111111111111111");
        person.setName87("小明111111111111111111111111111111");
        person.setName88("小明111111111111111111111111111111");
        person.setName89("小明111111111111111111111111111111");
        person.setName90("小明111111111111111111111111111111");
        person.setName91("小明111111111111111111111111111111");
        person.setName92("小明111111111111111111111111111111");
        person.setName93("小明111111111111111111111111111111");
        person.setName94("小明111111111111111111111111111111");
        person.setName95("小明111111111111111111111111111111");
        person.setName96("小明111111111111111111111111111111");
        person.setName97("小明111111111111111111111111111111");
        person.setName98("小明111111111111111111111111111111");
        person.setName99("小明111111111111111111111111111111");
        return person;
    }

//    @Override
//    public long batchSave(Integer size, Integer batchSize) {
//        long startTime = System.currentTimeMillis();
//
//        String sql = "insert into person (id, name, age, sex, address,name0,name1,name2,name3,name4,name5,name6,name7,name8,name9,name10,name11,name12,name13,name14,name15,name16,name17,name18,name19,name20,name21,name22,name23,name24,name25,name26,name27,name28,name29,name30,name31,name32,name33,name34,name35,name36,name37,name38,name39,name40,name41,name42,name43,name44,name45,name46,name47,name48,name49,name50,name51,name52,name53,name54,name55,name56,name57,name58,name59,name60,name61,name62,name63,name64,name65,name66,name67,name68,name69,name70,name71,name72,name73,name74,name75,name76,name77,name78,name79,name80,name81,name82,name83,name84,name85,name86,name87,name88,name89,name90,name91,name92,name93,name94,name95,name96,name97,name98,name99) values\n" +
//                "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//        try {
//            Connection connection = dataSource.getConnection();
//            connection.setAutoCommit(false); // 开启事务
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            for (int i = 1; i <= size; i++) {
//                prepareData(preparedStatement);
//                preparedStatement.addBatch();
//                if (i % batchSize == 0) {
//                    preparedStatement.executeBatch();
//                    preparedStatement.clearBatch();
//                }
//            }
//            preparedStatement.executeBatch(); // 执行剩余批次
//            connection.commit(); // 提交事务
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return System.currentTimeMillis() - startTime;
//    }

    private static void prepareData(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, 1);
        preparedStatement.setString(2, "1");
        preparedStatement.setInt(3, 1);
        preparedStatement.setInt(4, 1);
        preparedStatement.setString(5, "111");
        preparedStatement.setString(6, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(7, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(8, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(9, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(10, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(11, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(12, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(13, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(14, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(15, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(16, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(17, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(18, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(19, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(20, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(21, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(22, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(23, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(24, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(25, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(26, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(27, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(28, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(29, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(30, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(31, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(32, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(33, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(34, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(35, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(36, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(37, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(38, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(39, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(40, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(41, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(42, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(43, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(44, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(45, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(46, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(47, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(48, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(49, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(50, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(51, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(52, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(53, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(54, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(55, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(56, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(57, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(58, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(59, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(60, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(61, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(62, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(63, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(64, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(65, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(66, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(67, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(68, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(69, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(70, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(71, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(72, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(73, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(74, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(75, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(76, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(77, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(78, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(79, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(80, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(81, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(82, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(83, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(84, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(85, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(86, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(87, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(88, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(89, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(90, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(91, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(92, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(93, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(94, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(95, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(96, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(97, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(98, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(99, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(100, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(101, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(102, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(103, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(104, "小明11111111111111111111111111111111111111111111");
        preparedStatement.setString(105, "小明11111111111111111111111111111111111111111111");
    }

//    @Override
//    public long batchSave(Integer size, Integer batchSize) {
//        long startTime = System.currentTimeMillis();
//        List<Person> list = new ArrayList<>(1000);
//        for (int i = 1; i <= size; i++) {
//            Person person = new Person();
//            person.setId(i);
//            person.setAge(i);
//            person.setName("小明" + i);
//            person.setSex(1);
//            person.setAddress("地址" + i);
//            person.setName0("小明111111111111111111111111111111");
//            person.setName1("小明111111111111111111111111111111");
//            person.setName2("小明111111111111111111111111111111");
//            person.setName3("小明111111111111111111111111111111");
//            person.setName4("小明111111111111111111111111111111");
//            person.setName5("小明111111111111111111111111111111");
//            person.setName6("小明111111111111111111111111111111");
//            person.setName7("小明111111111111111111111111111111");
//            person.setName8("小明111111111111111111111111111111");
//            person.setName9("小明111111111111111111111111111111");
//            person.setName10("小明111111111111111111111111111111");
//            person.setName11("小明111111111111111111111111111111");
//            person.setName12("小明111111111111111111111111111111");
//            person.setName13("小明111111111111111111111111111111");
//            person.setName14("小明111111111111111111111111111111");
//            person.setName15("小明111111111111111111111111111111");
//            person.setName16("小明111111111111111111111111111111");
//            person.setName17("小明111111111111111111111111111111");
//            person.setName18("小明111111111111111111111111111111");
//            person.setName19("小明111111111111111111111111111111");
//            person.setName20("小明111111111111111111111111111111");
//            person.setName21("小明111111111111111111111111111111");
//            person.setName22("小明111111111111111111111111111111");
//            person.setName23("小明111111111111111111111111111111");
//            person.setName24("小明111111111111111111111111111111");
//            person.setName25("小明111111111111111111111111111111");
//            person.setName26("小明111111111111111111111111111111");
//            person.setName27("小明111111111111111111111111111111");
//            person.setName28("小明111111111111111111111111111111");
//            person.setName29("小明111111111111111111111111111111");
//            person.setName30("小明111111111111111111111111111111");
//            person.setName31("小明111111111111111111111111111111");
//            person.setName32("小明111111111111111111111111111111");
//            person.setName33("小明111111111111111111111111111111");
//            person.setName34("小明111111111111111111111111111111");
//            person.setName35("小明111111111111111111111111111111");
//            person.setName36("小明111111111111111111111111111111");
//            person.setName37("小明111111111111111111111111111111");
//            person.setName38("小明111111111111111111111111111111");
//            person.setName39("小明111111111111111111111111111111");
//            person.setName40("小明111111111111111111111111111111");
//            person.setName41("小明111111111111111111111111111111");
//            person.setName42("小明111111111111111111111111111111");
//            person.setName43("小明111111111111111111111111111111");
//            person.setName44("小明111111111111111111111111111111");
//            person.setName45("小明111111111111111111111111111111");
//            person.setName46("小明111111111111111111111111111111");
//            person.setName47("小明111111111111111111111111111111");
//            person.setName48("小明111111111111111111111111111111");
//            person.setName49("小明111111111111111111111111111111");
//            person.setName50("小明111111111111111111111111111111");
//            person.setName51("小明111111111111111111111111111111");
//            person.setName52("小明111111111111111111111111111111");
//            person.setName53("小明111111111111111111111111111111");
//            person.setName54("小明111111111111111111111111111111");
//            person.setName55("小明111111111111111111111111111111");
//            person.setName56("小明111111111111111111111111111111");
//            person.setName57("小明111111111111111111111111111111");
//            person.setName58("小明111111111111111111111111111111");
//            person.setName59("小明111111111111111111111111111111");
//            person.setName60("小明111111111111111111111111111111");
//            person.setName61("小明111111111111111111111111111111");
//            person.setName62("小明111111111111111111111111111111");
//            person.setName63("小明111111111111111111111111111111");
//            person.setName64("小明111111111111111111111111111111");
//            person.setName65("小明111111111111111111111111111111");
//            person.setName66("小明111111111111111111111111111111");
//            person.setName67("小明111111111111111111111111111111");
//            person.setName68("小明111111111111111111111111111111");
//            person.setName69("小明111111111111111111111111111111");
//            person.setName70("小明111111111111111111111111111111");
//            person.setName71("小明111111111111111111111111111111");
//            person.setName72("小明111111111111111111111111111111");
//            person.setName73("小明111111111111111111111111111111");
//            person.setName74("小明111111111111111111111111111111");
//            person.setName75("小明111111111111111111111111111111");
//            person.setName76("小明111111111111111111111111111111");
//            person.setName77("小明111111111111111111111111111111");
//            person.setName78("小明111111111111111111111111111111");
//            person.setName79("小明111111111111111111111111111111");
//            person.setName80("小明111111111111111111111111111111");
//            person.setName81("小明111111111111111111111111111111");
//            person.setName82("小明111111111111111111111111111111");
//            person.setName83("小明111111111111111111111111111111");
//            person.setName84("小明111111111111111111111111111111");
//            person.setName85("小明111111111111111111111111111111");
//            person.setName86("小明111111111111111111111111111111");
//            person.setName87("小明111111111111111111111111111111");
//            person.setName88("小明111111111111111111111111111111");
//            person.setName89("小明111111111111111111111111111111");
//            person.setName90("小明111111111111111111111111111111");
//            person.setName91("小明111111111111111111111111111111");
//            person.setName92("小明111111111111111111111111111111");
//            person.setName93("小明111111111111111111111111111111");
//            person.setName94("小明111111111111111111111111111111");
//            person.setName95("小明111111111111111111111111111111");
//            person.setName96("小明111111111111111111111111111111");
//            person.setName97("小明111111111111111111111111111111");
//            person.setName98("小明111111111111111111111111111111");
//            person.setName99("小明111111111111111111111111111111");
//            list.add(person);
//            if (i % batchSize == 0) {
//                personMapper.inserts(list);
//                list.clear();
//            }
//        }
//        return System.currentTimeMillis() - startTime;
//    }
}
