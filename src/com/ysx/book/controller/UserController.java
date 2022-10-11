package com.ysx.book.controller;
import com.ysx.book.pojo.Cart;
import com.ysx.book.pojo.User;
import com.ysx.book.service.CartItemService;
import com.ysx.book.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class UserController {

    private UserService userService ;
    private CartItemService cartItemService;
    public String login(String uname, String pwd, HttpSession session){
        //判断是否有用户
        User user = userService.login(uname, pwd);
        if (user!=null) {
            Cart cart = cartItemService.getCart(user);
            user.setCart(cart);
            session.setAttribute("currUser", user);
            return "redirect:book.do";
        }
//        }else{
//            session.setAttribute("loginerror", "用户名或密码错误");
//            return "user/login";
//        }

        return "user/login";

    }

    public String loginout(){
        return "/user/login";
    }

    public String regist(String uname, String pwd, String email, String kaptch, HttpSession session, HttpServletResponse response) throws IOException {
        // 验证码 更优秀的就是还需要验证用户的唯一性 邮箱的唯一性
        String kaptch_cur = (String) session.getAttribute("KAPTCHA_SESSION_KEY");

//        //先验证用户名的唯一性
//        List<String> names = userService.getNames();
//        if(names.contains(uname)){
//            setAlert("用户名已存在，请重新设置用户名。", response);
//            return "/user/regist";
//        }

        if(!kaptch_cur.equals(kaptch)||kaptch_cur==null){
            setAlert("验证码不正确!!!!", response);
            return "/user/regist";
        }else{
            User user = new User(uname, pwd, email);
            userService.regist(user);
            return "/user/regist_success";
        }
    }


    public void setAlert(String str, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
//            out.println("<script language='javascript'>alert('验证码不正确！');window.location.href='page.do?operate=page&page=user/regist';</script>");
        out.println("<script language='javascript'>alert('" + str + "');</script>");
    }

    public String ckUname(String uName){
        User user = userService.getUser(uName);
        if(user!=null){
            // 可以注册
            return "json:{'uname':'1'}";
        }else{
            // 用户已经存在
            return "json:{'uname':'0'}";
        }
    }

}
