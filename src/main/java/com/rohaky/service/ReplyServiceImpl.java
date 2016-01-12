package com.rohaky.service;

import com.rohaky.domain.ReplyVO;
import com.rohaky.persistence.ReplyDAO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by ilanian on 16. 1. 11.
 */
@Service
public class ReplyServiceImpl implements ReplyService {
    @Inject
    private ReplyDAO dao;

    @Override
    public void addReply(ReplyVO vo) throws Exception {
        dao.create(vo);
    }

    @Override
    public List<ReplyVO> listReply(Integer bno) throws Exception {
        return dao.list(bno);
    }

    @Override
    public void modifyReply(ReplyVO vo) throws Exception {
        dao.update(vo);
    }

    @Override
    public void removeReply(Integer rno) throws Exception {
        dao.delete(rno);
    }
}
