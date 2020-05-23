package com.example.jpa.web;

import com.example.jpa.dao.UserDao;
import com.example.jpa.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    public Map<String, Object> userList() {
        Map<String, Object> map = new HashMap<>();
        List<User> list = userDao.findAll();
        map.put("userList", list);
        return map;
    }

    @RequestMapping(value = "/getUserById", method = RequestMethod.GET)
    public Map<String, Object> getUserById(Integer userId) {
        Map<String, Object> map = new HashMap<>();
        List<User> list = userDao.getUserById(userId);
        map.put("userList", list);
        return map;
    }

    @RequestMapping(value = "/getUserId", method = RequestMethod.GET)
    public Object getUserId(String username) {
        Object object = userDao.getUserId(username);
        return object;
    }

//    @RequestMapping(value = "/login",method = RequestMethod.GET)
//    public String login(String username,String password){
//        User user = userDao.findByNameAndPassword(username,password);
//        if (user!=null){
//            return "登录成功";
//        }
//        return "登录失败";
//    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Map<String, Object> login(String username, String password) {
        User user = userDao.findByNameAndPassword(username, password);
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        return map;
    }

    //更新全部
    @RequestMapping(value = "/modifyUserAll", method = RequestMethod.GET)
    public Map<String, Object> modifyUserAll(@RequestParam("userId") Integer userId, @RequestParam("userName") String userName, @RequestParam("userPassword") String userPassword, @RequestParam("userAmount") Integer userAmount, @RequestParam("userPhone") String userPhone, @RequestParam("userHome") String userHome, @RequestParam("userCompany") String userCompany) {
        Map<String, Object> map = new HashMap<>();
        userDao.modifyUserAll(userId, userName, userPassword, userAmount, userPhone, userHome, userCompany);
        List<User> modifyUserAll = userDao.getUserById(userId);
        map.put("modifyUserAll", modifyUserAll);
        return map;
    }

    //修改用户信息
    @RequestMapping(value = "/modifyUserPart", method = RequestMethod.GET)
    public String modifyUserPart(@RequestParam("userPhone") String userPhone, @RequestParam("userHome") String userHome, @RequestParam("userCompany") String userCompany, @RequestParam("userId") Integer userId) {
        //Map<String, Object> map = new HashMap<>();
        userDao.modifyUserPart(userPhone, userHome, userCompany, userId);
        //List<User> modifyUserPart = userDao.getUserById(user.getUserId());
        //map.put("modifyUserPart",modifyUserPart);
        return "更新成功";
    }

    //充值
    @RequestMapping(value = "/modifyUserAmount", method = RequestMethod.GET)
    public String modifyUserAmount(@RequestParam("userId") Integer userId, @RequestParam("userAmount") Integer userAmount) {
        userDao.modifyUserAmount(userId, userAmount);
        return "充值成功";
    }

    //注册用户
    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public void addUser(@RequestParam("userName") String userName, @RequestParam("userPassword") String userPassword, @RequestParam("userAmount") Integer userAmount) {
        User user = new User();
        user.setUserAmount(userAmount);
        userDao.save(user);
    }
}
