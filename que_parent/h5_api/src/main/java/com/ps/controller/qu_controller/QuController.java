package com.ps.controller.qu_controller;

import com.ps.domain.Answer;
import com.ps.domain.Issue;
import com.ps.domain.Result;
import com.ps.service.member_service.UserService;
import com.ps.service.qu_sevice.QuService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qu")
public class QuController {

    @Reference(version = "1.0.0")
    private QuService quService;

    @Reference(version = "1.0.0")
    private UserService userService;

    @GetMapping("/allQue")
    public Result queryQuestion(){
        Result<List<Issue>> result = new Result<>();
        List<Issue> list = quService.queryAll();
        result.setBody(list);
        result.setCode(0);
        return result;
    }

    @GetMapping("/queryOneQue")
    public Result queryQueById(@RequestParam int id){
        Result<List<Answer>> result = new Result<>();
        List<Answer> list = quService.queryQueById(id);
        result.setBody(list);
        result.setCode(0);
        return result;
    }

    @PutMapping("/addAns")
    public Result addAns(@RequestBody Issue issue){
        Result result = new Result();
        quService.addAns(issue);
        result.setCode(0);
        result.setMessage("发布成功");
        return result;
    }

    @PostMapping("/answer")
    public Result answer(@RequestBody Answer answer){
        Result result = new Result();
        quService.answer(answer);
        result.setCode(0);
        return result;
    }

    @PostMapping("/adopt")
    public Result adopt(@RequestBody Answer answer,@RequestParam int id){
        Result result = new Result();
        quService.adopt(answer);
        int integral = quService.queryIntegral(answer);
        userService.addIntegral(answer,integral,id);
        result.setCode(0);
        return result;
    }
}
