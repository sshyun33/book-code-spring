package com.rohaky.persistence;

import com.rohaky.domain.BoardVO;
import com.rohaky.domain.SearchCriteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;


/**
 * Created by ilanian on 2016-01-07.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class BoardDAOImplTest {

    public static final Logger logger =
            LoggerFactory.getLogger(BoardDAOImplTest.class);

    @Inject
    BoardDAO dao;

    @Test
    public void testDynamic1() throws Exception {
        SearchCriteria cri = new SearchCriteria();
        cri.setPage(1);
        cri.setKeyword("글");
        cri.setSearchType("c");

        logger.info("============================");

        List<BoardVO> list = dao.listSearch(cri);

        for (BoardVO boardVO: list) {
            logger.info(boardVO.getBno() + ": " + boardVO.getTitle());
        }

        logger.info("============================");

        logger.info("COUNT: " + dao.listSearchCount(cri));
    }
}