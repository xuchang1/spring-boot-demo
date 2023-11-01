package com.xc.study;

import org.testng.TestNG;
import org.testng.TestNGUtils;
import org.testng.internal.thread.TestNGThreadFactory;
import org.testng.reporters.jq.TestNgXmlPanel;
import org.testng.xml.XmlSuite;
import org.testng.xml.internal.Parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public class TestngMain {

    public static void main(String[] args) throws IOException {
//        String path = TestngMain.class.getClassLoader().getResource("conf/case.xml").getPath();
        TestNG testNG = new TestNG();
        List<XmlSuite> suites = (List<XmlSuite>)(new Parser("classpath:conf/case.xml").parse());
        testNG.setXmlSuites(suites);
//        testNG.setTestSuites(Collections.singletonList("D:\\WorkSpace\\GitHub\\spring-boot-demo\\testng-demo\\src\\main\\resources\\conf\\case.xml"));
//        testNG.setTestSuites(Collections.singletonList(path));
//        FileInputStream fileInputStream = (FileInputStream) TestngMain.class.getClassLoader().getResourceAsStream("conf/case.xml");
        testNG.run();
    }
}
