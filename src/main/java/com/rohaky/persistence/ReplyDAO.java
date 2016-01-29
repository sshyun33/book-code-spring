package com.rohaky.persistence;

import com.rohaky.domain.Criteria;
import com.rohaky.domain.ReplyVO;
import com.sun.org.apache.regexp.internal.RE;

import java.util.List;

/**
 * Created by ilanian on 16. 1. 11.
 */
public interface ReplyDAO {
    public List<ReplyVO> list(Integer bno) throws Exception;
    public void create(ReplyVO vo) throws Exception;
    public void update(ReplyVO vo) throws Exception;
    public void delete(Integer rno) throws Exception;

    public List<ReplyVO> listPage(
            Integer bno, Criteria cri) throws Exception;
    public int count(Integer bno) throws Exception;
}
