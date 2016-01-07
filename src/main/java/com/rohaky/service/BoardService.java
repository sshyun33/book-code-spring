package com.rohaky.service;

import java.util.List;

import com.rohaky.domain.BoardVO;
import com.rohaky.domain.Criteria;
import com.rohaky.domain.SearchCriteria;
import com.rohaky.persistence.BoardDAO;

public interface BoardService {

	public void regist(BoardVO board) throws Exception;
	
	public BoardVO read(Integer bno) throws Exception;
	
	public void modify(BoardVO board) throws Exception;
	
	public void remove(Integer bno) throws Exception;
	
	public List<BoardVO> listAll() throws Exception;
	
	public List<BoardVO> listCriteria(Criteria cri) throws Exception;
	
	public int listCountCriteria(Criteria cri) throws Exception;

	public List<BoardVO> listSearchCriteria(SearchCriteria cri)
			throws Exception;

	public int listSearchCount(SearchCriteria cri) throws Exception;
}
