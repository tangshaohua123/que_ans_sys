package com.ps.controller.marketing_controller;

import com.ps.domain.*;
import com.ps.service.marketing_service.QuestionnaireService;
import com.ps.service.member_service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/qunaire")
public class QunaireController {

    @Reference(version = "1.0.0")
    private QuestionnaireService questionnaireService;

    @Reference(version = "1.0.0")
    private UserService userService;

    @PostMapping("/addQunaire")
    public Result addQunaire(@RequestBody List<QNaire> qNaire, @RequestBody Question question){
        Result result = new Result();
        questionnaireService.addQunaire(qNaire,question);
        return result;
    }

    @GetMapping("/getNaire")
    public Result<List> getNaire(@RequestParam int id){
        Result<List> result = new Result<>();
        List list = new ArrayList();
        Question question = questionnaireService.getTitle(id);
        List<QNaire> listQ = questionnaireService.getQue(id);
        list.add(question);
        list.add(listQ);
        result.setBody(list);
        return result;
    }

    @PostMapping("/answer")
    public Result answer(@RequestBody List<QuResult> quResult){
        Result result = new Result();
        questionnaireService.answer(quResult);
        return result;
    }

    @GetMapping("/invitation")
    public Result invitation(@RequestParam int id){
        Result result = new Result();
        String invitationCode = questionnaireService.invitation(id);
        result.setBody(invitationCode);
        return result;
    }

    @PostMapping("/seckill")
    public Result seckill(@RequestBody Order order) throws ParseException {
        Result result = questionnaireService.seckill(order);
        return result;
    }
}
