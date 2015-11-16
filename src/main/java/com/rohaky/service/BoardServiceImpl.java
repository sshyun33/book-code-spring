package com.rohaky.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.rohaky.domain.BoardVO;
import com.rohaky.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDAO dao;

	@Override
	public void regist(BoardVO board) throws Exception {
		dao.create(board);
	}

	@Override
	public BoardVO read(Integer bno) throws Exception {
		return dao.read(bno);
	}

	@Override
	public void modify(BoardVO board) throws Exception {
		dao.update(board);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		return dao.listAll();
	}

	@Override
	public void remove(Integer bno) throws Exception {
		dao.delete(bno);
	}

}
