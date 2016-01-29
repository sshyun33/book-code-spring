package com.rohaky.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by ilanian on 2016-01-29.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml"),
        @ContextConfiguration("file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")
})
public class RestControllerTest {

    @Inject
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testRestController() throws Exception {

        // Rest Get 요청을 검증
        // jsonpath 라이브러리 필요
        mockMvc.perform(get("/replies/800").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(handler().methodName("read"))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.bno", is(800)))
                .andExpect(jsonPath("$.rno", is(54)))
                .andExpect(jsonPath("$.replytext", is("Hello")));
    }
}
