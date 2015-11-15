package com.rohaky.controller;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rohaky.domain.BoardVO;
import com.rohaky.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class BoardDAOTest {
	
	private static final Logger logger = 
			LoggerFactory.getLogger(BoardDAOTest.class);

	@Inject
	private BoardDAO dao;
	

//	@Test
//	public void testCreate() throws Exception {
//		BoardVO board = new BoardVO();
//		board.setTitle("new title");
//		board.setContent("new text");
//		board.setWriter("user00");
//		dao.create(board);
//	}
	
//	@Test
//	public void testRead() throws Exception {
//		logger.info(dao.read(1).toString());
//	}
//	
//	@Test
//	public void testUpdate() throws Exception {
//		BoardVO board = new BoardVO();
//		board.setBno(1);
//		board.setTitle("updated title");
//		board.setContent("updated text");
//		dao.update(board);
//	}
	
//	@Test
//	public void testDelete() throws Exception {
//		dao.delete(1);
//	}
}
