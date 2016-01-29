package com.rohaky.controller;

import com.rohaky.domain.BoardVO;
import com.rohaky.service.BoardService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
public class ControllerTestWithMock {

    private MockMvc mockMvc;

    // 서비스 목 오브젝트를 선언
    @Mock
    BoardService mockedService;

    // 서비스 목 오브젝트가 반환할 도메인 목 객체 선언 (선택)
    @Mock
    BoardVO mockedVO;

    // 서비스 목 오브젝트를 주입(DI)받을 컨트롤러 객체 선언
    @InjectMocks
    BoardController boardController;

    @Before
    public void setup() throws Exception {
        // Mockito 어노테이션(@Mock, @InjectMocks등)을 사용
        MockitoAnnotations.initMocks(this);

        // 단일 컨트롤러를 위한 스탠드얼론 방식으로 mockMVC 객체 생성
        this.mockMvc = MockMvcBuilders.standaloneSetup(boardController).build();
        // this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testReadPageWithMock() throws Exception {

        // 목 객체의 메서드를 스텁화(Method Stubbing)
        when(mockedVO.getBno()).thenReturn(100);
        when(mockedVO.getTitle()).thenReturn("Title Example");
        when(mockedService.read(100)).thenReturn(mockedVO);

        // "/board/read"의 Get 요청에 대한 HTTP상태, 뷰네임, 모델정보 검증
        mockMvc.perform(get("/board/read").param("bno", "100"))
                .andExpect(status().isOk())
                .andExpect(view().name("board/read"))
                .andExpect(model().attribute("boardVO", is(instanceOf(BoardVO.class))))
                .andExpect(model().attribute("boardVO", hasProperty("bno", is(100))))
                .andExpect(model().attribute("boardVO", hasProperty("title", is("Title Example"))));

        // 목 객체의 특정 메서드의 호출 여부에 대한 검증
        verify(mockedVO).getBno();
        verify(mockedVO, times(1)).getTitle();
        verify(mockedService, times(1)).read(100);
    }

    @Test
    public void testRegisterPage() throws Exception {
        // Post 요청 검증
        mockMvc.perform(post("/board/register").param("bno", "200").param("title", "Java Book")
                .param("content", "Java Basics").param("writer", "James"))
                .andExpect(handler().methodName("registPOST"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/board/listAll"))
                .andExpect(flash().attribute("msg", is("success")));
    }
}