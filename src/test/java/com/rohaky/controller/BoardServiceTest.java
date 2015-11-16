package com.rohaky.controller;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rohaky.domain.BoardVO;
import com.rohaky.service.BoardService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
public class BoardServiceTest {

	private static final Logger logger =
			LoggerFactory.getLogger(BoardServiceTest.class);
			
	@Inject 
	private BoardService svr;
	

//	@Test
//	public void testCreate() throws Exception {
//		BoardVO vo = new BoardVO();
//		vo.setTitle("Service Title");
//		vo.setContent("Service Content");
//		vo.setWriter("sshyun");
//		svr.regist(vo);
//	}
	
	@Test
	public void testRead() throws Exception {
		logger.info(svr.read(3).toString());
	}

//	@Test
//	public void testModify() throws Exception {
//		BoardVO vo = new BoardVO();
//		vo.setBno(2);
//		vo.setTitle("New Service Title");
//		vo.setContent("New Service Content");
//		vo.setWriter("New sshyun");
//		svr.modify(vo);
//	}
	
//	@Test
//		public void testRemove() throws Exception {
//		svr.remove(2);
//	}
	
//	@Test
//	public void testListAll() throws Exception {
//		List<BoardVO> boardList = svr.listAll();
//		 
//		for (BoardVO vo : boardList) {
//			logger.info(vo.toString());
//		}
//	}
}
