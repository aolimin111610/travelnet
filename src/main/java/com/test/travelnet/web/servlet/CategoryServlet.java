package com.test.travelnet.web.servlet;

import com.test.travelnet.domain.Category;
import com.test.travelnet.service.CategoryService;
import com.test.travelnet.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {
    private CategoryService service = new CategoryServiceImpl();
    /***
     * 查询所有
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public  void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> cs = service.findAll();
        writeValue(cs,response);
    }
}
