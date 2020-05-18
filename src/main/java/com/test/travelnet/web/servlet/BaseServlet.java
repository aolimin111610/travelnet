package com.test.travelnet.web.servlet;

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
 * @Description
 * @Author Alm
 * @Date 2020/5/16 14:05
 * @Version V1.0
 */
public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String methodName = requestURI.substring(requestURI.lastIndexOf("/") + 1);
        Method method = null;
        try {
            method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,request,response);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    /***
     * 将传入的对象转换为json格式字符串，并返回给调用者
     * @param object
     * @return
     * @throws JsonProcessingException
     */
    public String writeValueAsString(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
       return mapper.writeValueAsString(object);
    }

    /***
     * 将传入的对象转为json格式字符串，返回给客户端
     * @param object
     * @param response
     * @throws IOException
     */
    public void writeValue(Object object,HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),object);

    }
}
