package com.rohaky.controller;

import com.rohaky.domain.ReplyVO;
import com.rohaky.service.ReplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.InterceptingClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.rmi.server.RMIClassLoader;
import java.util.List;

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
        return entity;
    }

    @RequestMapping(value = "/all/{bno}", method = RequestMethod.GET)
    public ResponseEntity<List<ReplyVO>> list(
            @PathVariable("bno") Integer bno) {

        ResponseEntity<List<ReplyVO>> entity = null;
        try {
            entity = new ResponseEntity<>(
                    service.listReply(bno), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @RequestMapping(value = "/{rno}",
            method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<String> update(
            @PathVariable("rno") Integer rno,
            @RequestBody ReplyVO vo) {

        ResponseEntity<String> entity = null;

        try {
            vo.setRno(rno);
            service.modifyReply(vo);

            entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>(
                    e.getMessage(), HttpStatus.BAD_REQUEST
            );
        }
        return entity;
    }

    @RequestMapping(value = "/{rno}", method = RequestMethod.DELETE)
    public ResponseEntity<String> remove(
            @PathVariable("rno") Integer rno) {

        ResponseEntity<String> entity = null;
        try {
            service.removeReply(rno);
            entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }
}
