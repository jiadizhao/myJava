package cn.zhaofan.travel.web.servlet;

import cn.zhaofan.travel.service.CategoryService;
import cn.zhaofan.travel.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet{

    private CategoryService categoryService=new CategoryServiceImpl();
    public void findAll(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        List category = categoryService.findCategory();
        writeValue(category,response);
    }
}
