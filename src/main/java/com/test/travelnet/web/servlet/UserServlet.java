package com.test.travelnet.web.servlet;


import com.test.travelnet.domain.ResultInfo;
import com.test.travelnet.domain.User;
import com.test.travelnet.service.UserService;
import com.test.travelnet.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;


/**
 * @Description
 * @Author Alm
 * @Date 2020/5/16 14:18
 * @Version V1.0
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService service = new UserServiceImpl();

    /**
     *  注册方法
     * @param request
     * @param response
     * @throws Exception
     */
    public void register(HttpServletRequest request,HttpServletResponse response) throws Exception{
        checkCode(request,response);

        User user = new User();
        encapsulation(user,request,response);
        Boolean flag = service.register(user);
        ResultInfo info = new ResultInfo();
        if(flag){
            //注册成功
            info.setFlag(true);
        }else{
            info.setFlag(false);
            info.setErrorMsg("注册失败，用户名已被注册过！");
        }

        String json = writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    /**
     * 登录方法
     * @param request
     * @param response
     * @throws Exception
     */
    public void login(HttpServletRequest request,HttpServletResponse response) throws Exception{
        checkCode(request,response);


        User user = new User();
        encapsulation(user,request,response);
        User u = service.login(user);
        ResultInfo info = new ResultInfo();
        if(u == null){
            info.setFlag(false);
            info.setErrorMsg("用户名或者密码错误");
        }
        if(u != null && !"Y".equals(u.getStatus())){
            info.setFlag(false);
            info.setErrorMsg("您的账号尚未激活，请登录邮箱进行激活");
        }
        if(u != null && "Y".equals(u.getStatus())){
            request.getSession().setAttribute("user",u);
            info.setFlag(true);
        }
        writeValue(info,response);
    }

    /**
     * 首页显示用户名的方法
     * @param request
     * @param response
     * @throws Exception
     */
    public void findOne(HttpServletRequest request,HttpServletResponse response) throws Exception{
        Object user = request.getSession().getAttribute("user");
        writeValue(user,response);
    }

    /**
     * 退出方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.销毁session
        request.getSession().invalidate();

        //2.跳转登录页面
        response.sendRedirect(request.getContextPath()+"/login.html");
    }


    /***
     * 获取参数数据并将参数数据保存到对象中
     * @param Object
     * @param request
     * @param response
     * @throws Exception
     */
    public void encapsulation(Object Object,HttpServletRequest request, HttpServletResponse response) throws Exception{
        Map<String, String[]> map = request.getParameterMap();
        try {
            BeanUtils.populate(Object,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /***
     * 检验验证码
     * @param request
     * @param response
     */
    public void checkCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String check = request.getParameter("check");
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");

        //销毁session中的验证码
        session.removeAttribute("CHECKCODE_SERVER");

        if(checkcode_server==null ||!checkcode_server.equalsIgnoreCase(check) ){
            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误，请重新输入或刷新验证码输入");

            String json = writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }

    }
}
