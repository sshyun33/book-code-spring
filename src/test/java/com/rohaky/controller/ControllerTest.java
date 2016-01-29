package com.rohaky.controller;

import com.rohaky.domain.BoardVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
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
import java.util.ArrayList;

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
public class ControllerTest {

    // 스프링 컨텍스트 주입
    @Inject
    private WebApplicationContext wac;

    // 스프링 MVC 테스트를 위한 Entry Point 역할을 하는 객체
    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        // mockMvc 객체 생성
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testReadPage() throws Exception {

        //특정 URL에 대한 GET 요청 후 응답 상태 검증
        mockMvc.perform(get("/board/read?bno=827"))
                .andExpect(status().isOk());

        // 파라미터 지정 방식 변경
        mockMvc.perform(get("/board/read").param("bno", "827").param("perPageNum", "10"))
                .andExpect(status().isOk());

        // 여러가지 응답 상태 겁증
        mockMvc.perform(get("/board/read").param("bno", "827"))
                .andExpect(status().is(200))
                .andExpect(status().isOk())
                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(not(404)));

        // 테스트 처리 정보를 출력
        mockMvc.perform(get("/board/read").param("bno", "827"))
                .andDo(print());

        // 핸들러 정보 검증
        mockMvc.perform(get("/board/read").param("bno", "827"))
                .andExpect(handler().handlerType(BoardController.class))
                .andExpect(handler().methodName("read"));

        // 뷰 정보 검증
        mockMvc.perform(get("/board/read").param("bno", "827"))
                .andExpect(view().name(is("board/read")))
                .andExpect(forwardedUrl("/WEB-INF/views/board/read.jsp"));

        // 모델 정보 검증
        mockMvc.perform(get("/board/read").param("bno", "827"))
                .andExpect(model().attributeExists("boardVO"))
                .andExpect(model().attribute("boardVO", is(instanceOf(BoardVO.class))))
                .andExpect(model().attribute("boardVO", is(notNullValue())))
                .andExpect(model().attribute("boardVO", hasProperty("bno")))
                .andExpect(model().attribute("boardVO", hasProperty("bno", is(827))));
    }

    @Test
    public void testListPage() throws Exception {

        // 게시판 목록을 반환하는 listPage에 대한 검증
        mockMvc.perform(get("/board/listPage"))
                .andExpect(status().isOk())
                .andExpect(handler().methodName("listPage"))
                .andExpect(view().name("board/listPage"))
                .andExpect(forwardedUrl("/WEB-INF/views/board/listPage.jsp"))
                .andExpect(model().attribute("list", hasSize(10)))
                .andExpect(model().attribute("list", is(instanceOf(ArrayList.class))))
                .andExpect(model().attribute("list", hasItem(
                        allOf(
                                is(instanceOf(BoardVO.class)),
                                is(notNullValue()),
                                hasProperty("title"),
                                hasProperty("content", is(notNullValue())),
                                not(hasProperty("noneField"))
                        )
                )));

        // mockMvc 객체로부터 ModelAndView 객체를 직접 받아서 내용 확인
        // 테스트 입력을 수행(perform()) 후 결과 정보를 mvcResult 객체 획득
        MvcResult mvcResult = mockMvc.perform(get("/board/listPage")).andReturn();

        // mvcResult 객체로부터 ModelAndView 객체 획득
        ModelAndView modelAndView = mvcResult.getModelAndView();

        // ModelAndView 객체를 통해 뷰 정보 확인
        assertThat(modelAndView.hasView(), is(true));
        System.out.println("View Name: " + modelAndView.getViewName());

        // ModelAndView 객체를 통해 모델 정보 확인
        System.out.println("*** Model's Key & Value ***");
        System.out.println("Model Size: " + modelAndView.getModel().size());

        modelAndView.getModel().forEach(
                (k, v) -> {
                    System.out.println("=======================");
                    System.out.println("Key: " + k + "\nValue: " + v);
                });

        System.out.println("=======================");

        // mvcResult 객체로부터 HttpServletRequest 객체 획득 후 검증
        MockHttpServletRequest request = mvcResult.getRequest();
        assertThat(request.getPathInfo(), is("/board/listPage"));
    }
}