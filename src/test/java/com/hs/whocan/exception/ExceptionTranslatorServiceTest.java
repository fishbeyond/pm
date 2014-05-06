package com.hs.whocan.exception;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.junit.Assert.*;

public class ExceptionTranslatorServiceTest {

    private  ExceptionTranslatorService exceptionTranslatorService;

    @Before
    public void setUp() throws Exception {
        Properties properties = new Properties();
        properties.setProperty("replaceTest", "${placeholder} test message.");
        exceptionTranslatorService = new ExceptionTranslatorService();
        exceptionTranslatorService.setMessages(properties);
    }

    @Test
    public void testTrans() throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("placeholder", "hello");
        String actual = exceptionTranslatorService.trans("replaceTest", map);
        String expected = "hello test message.";
        Assert.assertEquals(expected, actual);
    }
}