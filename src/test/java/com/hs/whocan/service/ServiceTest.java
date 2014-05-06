package com.hs.whocan.service;

import com.hs.whocan.component.account.security.SecurityComponent;
import com.hs.whocan.component.account.user.UserComponent;
import com.hs.whocan.component.account.user.dao.User;
import com.hs.whocan.json.JsonResultService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.json.JacksonJsonObjectMapperProvider;
import org.springframework.integration.support.json.JsonObjectMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Created by yinwenbo on 14-5-5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:service/test-context.xml"})
@WebAppConfiguration
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class ServiceTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private JsonResultService jsonResultService;

    @Autowired
    private UserComponent userComponent;

    @Autowired
    private SecurityComponent securityComponent;

    private JsonObjectMapper jsonObjectMapper = JacksonJsonObjectMapperProvider.newInstance();

    private MockMvc mockMvc;
    private String token;
    private User user;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        user = userComponent.createUserInfo("testUserData");
        token = securityComponent.createAccessInfo(user.getUserId());
    }

    public MockHttpServletRequestBuilder getSignInRequest(String path) {
        return get(path).param("token", token);
    }

    public MockHttpServletRequestBuilder getRequest(String path) {
        return get(path);
    }

    public ResultActions perform(MockHttpServletRequestBuilder builder) throws Exception {
        return mockMvc.perform(builder);
    }

    public String getResponse(MockHttpServletRequestBuilder builder) throws Exception {
        MvcResult mvcResult = mockMvc.perform(builder).andReturn();
        return mvcResult.getResponse().getContentAsString();
    }

    public String getSuccessJson(Object data) throws Exception {
        return getJson(jsonResultService.getSuccessResult(data));
    }

    public String getExceptionJson(Exception e) throws Exception {
        return getJson(jsonResultService.getExceptionResult(e));
    }

    public String getJson(Object object) throws Exception {
        return jsonObjectMapper.toJson(object);
    }

}
