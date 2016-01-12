package com.rohaky.controller;

import com.rohaky.domain.ReplyVO;
import com.rohaky.service.ReplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.tags.form.SelectTag;

import javax.inject.Inject;
import javax.swing.plaf.basic.BasicScrollPaneUI;

/**
 * Created by ilanian on 16. 1. 11.
 */
@RestController
@RequestMapping("/replies")
public class ReplyController {

    @Inject
    private ReplyService service;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody ReplyVO vo) {
        System.out.println("in reply create");
        System.out.println(vo);

        ResponseEntity<String> entity = null;
        try {
            service.addReply(vo);
            entity = new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return  entity;
    }
}
