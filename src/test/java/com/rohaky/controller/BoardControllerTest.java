package com.rohaky.controller;

import com.rohaky.domain.BoardVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Map;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by ilanian on 2016-01-22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml"),
        @ContextConfiguration("file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")
})
public class BoardControllerTest {

    @Inject
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testReadPage() throws Exception {

        mockMvc.perform(get("/board/read?bno=827"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/board/read").param("bno", "827"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/board/read").param("bno", "827"))
                .andExpect(status().is(200))
                .andExpect(status().isOk())
                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(not(404)));

        mockMvc.perform(get("/board/read").param("bno", "827"))
                .andDo(print());

        mockMvc.perform(get("/board/read").param("bno", "827"))
                .andExpect(view().name(is("board/read")))
                .andExpect(forwardedUrl("/WEB-INF/views/board/read.jsp"));

        mockMvc.perform(get("/board/read").param("bno", "827"))
                .andExpect(model().attributeExists("boardVO"))
                .andExpect(model().attribute("boardVO", is(instanceOf(BoardVO.class))))
                .andExpect(model().attribute("boardVO", is(notNullValue())))
                .andExpect(model().attribute("boardVO", hasProperty("bno")))
                .andExpect(model().attribute("boardVO", hasProperty("bno", is(827))));
    }

    @Test
    public void testListPage() throws Exception {

        mockMvc.perform(get("/board/listPage"))
                .andExpect(status().isOk())
                .andExpect(view().name("board/listPage"))
                .andExpect(forwardedUrl("/WEB-INF/views/board/listPage.jsp"))
                .andExpect(model().attribute("list", hasSize(10)))
                .andExpect(model().attribute("list", is(instanceOf(ArrayList.class))))
                .andExpect(model().attribute("list", hasItem(
                        allOf(
                                is(instanceOf(BoardVO.class)),
                                is(notNullValue()),
                                hasProperty("title", is(notNullValue())),
                                hasProperty("content", is(notNullValue())),
                                not(hasProperty("noneField"))
                        )
                )));

        MvcResult mvcResult = mockMvc.perform(get("/board/listPage")).andReturn();

        ModelAndView modelAndView = mvcResult.getModelAndView();

        assertThat(modelAndView.hasView(), is(true));
        System.out.println("View Name: " + modelAndView.getViewName());

        System.out.println("*** Model's Key & Value ***");
        System.out.println("Model Size: " + modelAndView.getModel().size());

        StringBuilder keyValueMsg = new StringBuilder();
        for (Map.Entry<String, Object> entry :
                modelAndView.getModel().entrySet()) {

            String key = entry.getKey();
            Object value = entry.getValue();

            keyValueMsg.setLength(0);
            keyValueMsg.append("Key: ").append(key)
                    .append("\nValue: ").append(value.toString());

            System.out.println("-----------------------");
            System.out.println(keyValueMsg.toString());
        }
        System.out.println("=======================");

        MockHttpServletRequest request = mvcResult.getRequest();
        assertThat(request.getPathInfo(), is("/board/listPage"));
    }
}