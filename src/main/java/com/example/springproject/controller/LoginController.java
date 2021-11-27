package com.example.springproject.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * TODO
 *
 * @author Administrator
 * @date 2021/11/27
 */
@RequestMapping("/loginContro")
@RestController
public class LoginController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/login")
    public String login(@RequestParam String userName, @RequestParam String passWord){
        String sql = "select * from user_account_number where user_name = '"+userName+"'";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        if (maps == null && maps.isEmpty()){
            return "该用户不存在!";
        }
        Map<String,Object> map = maps.get(0);
        if (passWord.equals(map.get("pass_word"))){
            return "输入正确!";
        }
        return "密码错误";
    }

    @RequestMapping("/register")
    public String register(@RequestParam String userName, @RequestParam String passWord){

        if (StringUtils.isBlank(userName)) {
            return "用户名不能为空!";
        }
        if (StringUtils.isBlank(passWord)){
            return "密码不能为空!";
        }

        String sql = "select * from user_account_number where user_name = '"+userName+"'";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        if (maps != null && maps.size() >0){
            return "该用户已被注册";
        }
        //passWord = "加密";
        String insertSql = "insert into user_account_number ( id , user_name , pass_word ) value ('"+ UUID.randomUUID().toString() +"','"+userName+"','"+passWord+"')";
        if (jdbcTemplate.update(insertSql) > 0){
            return "注册成功";
        }

        return "注册失败!";
    }

    @RequestMapping("/forgetPassword")
    public String forgetPassword(@RequestParam String userName, @RequestParam String newPassword){
        String sql = "update user_account_number set pass_word = '"+newPassword+"' where user_name = '"+userName+"'";
        if (jdbcTemplate.update(sql) > 0){
            return "密码修改成功";
        }
        return "密码修改失败";
    }



}
