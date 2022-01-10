package com.xc.study.controller;

import com.xc.study.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class LoginController {

//    @PostMapping("/login")
//    public String login(User user) {
//        if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword())) {
//            return "请输入用户名和密码！";
//        }
//        //用户认证信息
//        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
//                user.getUserName(),
//                user.getPassword()
//        );
//        try {
//            //进行验证，这里可以捕获异常，然后返回对应信息
//            subject.login(usernamePasswordToken);
////            subject.checkRole("admin");
////            subject.checkPermissions("query", "add");
//        } catch (UnknownAccountException e) {
//            log.error("用户名不存在！", e);
//            return "用户名不存在！";
//        } catch (AuthenticationException e) {
//            log.error("账号或密码错误！", e);
//            return "账号或密码错误！";
//        } catch (AuthorizationException e) {
//            log.error("没有权限！", e);
//            return "没有权限";
//        }
//        return "login success";
//    }

    @ResponseBody
    @PostMapping("/login")
    public String login(HttpServletRequest request) {
        // <1> 判断是否已经登录
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() != null) {
            return "你已经登录账号：" + subject.getPrincipal();
        }

        // <2> 获得登录失败的原因
        String shiroLoginFailure = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        // 翻译成人类看的懂的提示
        String msg = "";
        if (UnknownAccountException.class.getName().equals(shiroLoginFailure)) {
            msg = "账号不存在";
        } else if (IncorrectCredentialsException.class.getName().equals(shiroLoginFailure)) {
            msg = "密码不正确";
        } else if (LockedAccountException.class.getName().equals(shiroLoginFailure)) {
            msg = "账号被锁定";
        } else if (ExpiredCredentialsException.class.getName().equals(shiroLoginFailure)) {
            msg = "账号已过期";
        } else {
            msg = "未知";
            log.error("[login][未知登录错误：{}]", shiroLoginFailure);
        }
        return "登录失败，原因：" + msg;
    }

    @ResponseBody
    @GetMapping("/login_success")
    public String loginSuccess() {
        return "登录成功";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login.html";
    }

    @ResponseBody
    @GetMapping("/unauthorized")
    public String unauthorized() {
        return "你没有权限";
    }

    @RequiresRoles("admin")
    @GetMapping("/admin")
    public String admin() {
        return "admin success!";
    }

    @RequiresPermissions("query")
    @GetMapping("/index")
    public String index() {
        return "index success!";
    }

    @RequiresPermissions("add")
    @GetMapping("/add")
    public String add() {
        return "add success!";
    }
}
