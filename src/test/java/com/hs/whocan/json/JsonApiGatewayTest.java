package com.hs.whocan.json;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.json.JacksonJsonObjectMapperProvider;
import org.springframework.integration.support.json.JsonObjectMapper;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Created by yinwenbo on 14-4-30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:json/test-context.xml"})
@WebAppConfiguration
public class JsonApiGatewayTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private JsonResultService jsonResultService;

    private JsonObjectMapper jsonObjectMapper = JacksonJsonObjectMapperProvider.newInstance();

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testServiceNotExistsChannel() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/service/not_exists.json")).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String expected = jsonObjectMapper.toJson(jsonResultService.getServiceNotExistsResult());
        String actual = response.getContentAsString();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testServiceSuccess() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/json/apitest.json").param("name", "test")).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String expected = jsonObjectMapper.toJson(jsonResultService.getSuccessResult("test"));
        String actual = response.getContentAsString();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testServiceTransformerException() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/json/apitest.json").param("name", "test").param("rate", "abc")).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String expected = jsonObjectMapper.toJson(jsonResultService.getSuccessResult("test"));
        String actual = response.getContentAsString();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testServiceExecuteException() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/json/apitest.json").param("name", "exception")).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String expected = jsonObjectMapper.toJson(jsonResultService.getExceptionResult(new RuntimeException("exception")));
        String actual = response.getContentAsString();
        Assert.assertEquals(expected, actual);
    }


}
