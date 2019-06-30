package cn.zhaofan.travel.web.servlet;

import cn.zhaofan.travel.domain.ResultInfo;
import cn.zhaofan.travel.domain.User;
import cn.zhaofan.travel.service.UserService;
import cn.zhaofan.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();
    private ObjectMapper mapper=new ObjectMapper();
    //确认邮件时
    public void activeUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        response.setContentType("text/html;charset=utf-8");
        if (code == null || code.length() == 0) {
            response.getWriter().write("注册出错,请联系管理员!!");
            return;
        }
        boolean active = userService.active(code);
        if (active) {
            response.sendRedirect(request.getContextPath() + "/index.html");
        } else {
            response.getWriter().write("请确认该邮箱是您注册提供的邮箱");
        }
    }

    //退出
    public void exitUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();//销毁session对象
        //跳转登录页面
        response.sendRedirect(request.getContextPath() + "/login.html");
    }

    //查找用户名,在页面显示
    public void findUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            response.setContentType("application/json;charset=utf-8");
            mapper.writeValue(response.getOutputStream(), user.getName());
        }
    }

    //登录判断
    public void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String check = request.getParameter("check");
        response.setContentType("application/json;charset=utf-8");
        HttpSession session = request.getSession();
        ResultInfo info=new ResultInfo();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        if(check==null||check.isEmpty()||!(checkcode_server.equalsIgnoreCase(check))){
            info.setFlag(false);
            info.setErrorMsg("验证码错误!!");
            mapper.writeValue(response.getOutputStream(),info);
            return;
        }
        Map<String, String[]> map = request.getParameterMap();
        User user=new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        user=userService.loginUserService(user);
        if(user==null){
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        }else if("N".equals(user.getStatus())||user.getStatus()==null){
            info.setFlag(false);
            info.setErrorMsg("用户没有授权,请先到邮箱中确认.");
        }else if("Y".equals(user.getStatus())){
            info.setFlag(true);
            request.getSession().setAttribute("user",user);
        }
        mapper.writeValue(response.getOutputStream(),info);
    }

    //注册用户
    public void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("application/json;charset=utf-8");
        String check = request.getParameter("check");
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        System.out.println("checkcode_server..."+checkcode_server);
        System.out.println("check..."+check);
        session.removeAttribute("CHECKCODE_SERVER");
        ResultInfo info= info = new ResultInfo();
        if (check!=null&&!("".equals(check))&&checkcode_server.equalsIgnoreCase(check)) {//判断验证码是否正确
            Map<String, String[]> parameterMap = request.getParameterMap();
            User user=new User();
            try {
                BeanUtils.populate(user,parameterMap);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            boolean b = userService.registerUsesr(user);
            if(b){
                info.setFlag(true);
            }else{
                info.setFlag(false);
                info.setErrorMsg("用户已存在");
            }
        }else{
            info.setFlag(false);
            info.setErrorMsg("验证码不正确");
        }
        String s = mapper.writeValueAsString(info);
        System.out.println(s);
        response.getWriter().write(s);
    }
}