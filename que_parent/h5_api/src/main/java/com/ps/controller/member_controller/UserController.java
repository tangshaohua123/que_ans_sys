package com.ps.controller.member_controller;

import com.ps.domain.Integral;
import com.ps.domain.Result;
import com.ps.domain.User;
import com.ps.service.member_service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Reference(version = "1.0.0")
    private UserService userService;

    @PostMapping("/register")
    public Result register(@RequestBody User user) throws ParseException {
        Result result = new Result();
        int code = userService.register(user);
        result.setCode(code);
        result.setMessage("注册成功!");
        return result;
    }

    @GetMapping("/integral")
    public Result queryAllIntegral(@RequestParam int id){
        Result<List<Integral>> result = new Result<>();
        List<Integral> integral = userService.queryAllIntegral(id);
        result.setCode(0);
        result.setBody(integral);
        return result;
    }
}
