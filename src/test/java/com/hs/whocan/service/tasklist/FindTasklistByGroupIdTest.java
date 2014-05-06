package com.hs.whocan.service.tasklist;

import com.hs.whocan.service.ServiceTest;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.ArrayList;
import java.util.Objects;

public class FindTasklistByGroupIdTest extends ServiceTest {

    @Before
    public void setUp() throws Exception {
        System.out.println("setup");
    }

    @Test
    public void testFindTasklistByGroupIdNotExists() throws Exception {
        String expected = getSuccessJson(new ArrayList<Objects>(0));
        String actual = getResponse(getSignInRequest("/tasklist/findByGroupId.json").param("groupId", "notExists"));
        Assert.assertEquals(expected, actual);
    }

//    @Test
    public void testTaskCreate() throws Exception {
        String expected = getSuccessJson(new ArrayList<Objects>(0));
        MockHttpServletRequestBuilder builder = getSignInRequest("/tasklist/create.json")
                .param("title", "test tasklist title")
                .param("groupId", "testGroupId")
                .param("description", "description")
                .param("rate", "0")
                .param("top", "0")
                .param("deadline", "0")
                .param("parentId", "")
                .param("type", "");
//        perform(builder).andExpect(jsonPath("data").exists());
        String actual = getResponse(builder);
        Assert.assertEquals(expected, actual);
    }
}