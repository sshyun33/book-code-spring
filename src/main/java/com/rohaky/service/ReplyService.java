package com.rohaky.service;

import com.rohaky.domain.ReplyVO;

import java.util.List;

/**
 * Created by ilanian on 16. 1. 11.
 */
public interface ReplyService {

    public void addReply(ReplyVO vo) throws Exception;

    public List<ReplyVO> listReply(Integer bno) throws Exception;
    public void modifyReply(ReplyVO vo) throws Exception;

    public void removeReply(Integer rno) throws Exception;
}
