package cn.zhaofan.travel.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 减少servlet数量,利用反射,将获取到的url进行拆解,获取到方法名,在一个servlet方法中实现代码
 *
 * 将UserServlet继承UserBase方法,当UserServlet方法创建时,会自动调用父类的service方法,然后利用反射将子类的方法进行调用
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //完成方法分发

        //1.获取请求url
        String requestURI = req.getRequestURI();
        //2.获取方法名
        String method = requestURI.substring(requestURI.lastIndexOf("/") + 1);
        System.out.println("cn.zhaofan.travel.web.servlet.baseServlet.service.Method..."+method);
        //this是谁调用我,我就代表谁,
        try {
            //3.利用反射,获取方法,参数
            Method method1 = this.getClass().getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            //执行方法
            method1.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    //obj序列化json,向客户端传回
    public void writeValue(Object obj,HttpServletResponse response){
        response.setContentType("application/json;charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(response.getOutputStream(),obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String writeValueAsString(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}
