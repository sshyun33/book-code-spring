package com.rohaky.controller;

import com.rohaky.domain.BoardVO;
import com.rohaky.domain.ReplyVO;
import com.rohaky.service.BoardService;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;

import static org.junit.Assert.*;
import org.springframework.web.servlet.ModelAndView;

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
    public void testFirst() throws Exception {
/*        mockMvc.perform(get("/incorrectPage/all/800").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().is4xxClientError());

        mockMvc.perform(get("/incorrectPage/all/800").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isNotFound());*/


/*        mockMvc.perform(get("/replies/800").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(content().contentType("application/json;charset=UTF-8"));
               .andExpect(jsonPath("$.replytext").value("test1212"));*/

        MvcResult mvcResult = mockMvc.perform(get("/board/read").param("bno", "826"))
                .andExpect(status().isOk()).andReturn();

        ModelAndView mv = mvcResult.getModelAndView();
        System.out.println(mv.getModel().get("boardVO").toString());

        mockMvc.perform(get("/board/read").param("bno", "826"))
                .andExpect(model().attribute("boardVO", is(instanceOf(BoardVO.class))))
                .andExpect(view().name("board/read"));


    }




}