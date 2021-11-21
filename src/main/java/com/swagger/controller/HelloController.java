package com.swagger.controller;

import com.swagger.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CJYong
 * @create 2021-11-21 14:45
 */
@Api(tags = "hello控制类")
@RestController
public class HelloController {

    @ApiOperation("返回hello,swagger")
    @GetMapping("/hello")
    public String hello(@ApiParam("用户名") String username){
        return "hello,swagger" + username;
    }

    //只要我们的接口中的返回值存在实体类，就会被扫描到swagger中
    @PostMapping("/user")
    public User user(){
        return new User("张三","123456");
    }

}
