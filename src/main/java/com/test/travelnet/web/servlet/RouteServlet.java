package com.test.travelnet.web.servlet;

import com.test.travelnet.domain.PageBean;
import com.test.travelnet.domain.Route;
import com.test.travelnet.domain.User;
import com.test.travelnet.service.FavoriteService;
import com.test.travelnet.service.RouteService;
import com.test.travelnet.service.impl.FavoriteServiceImpl;
import com.test.travelnet.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {

    private RouteService routeService = new RouteServiceImpl();

    private FavoriteService favoriteService = new FavoriteServiceImpl();
    /***
     * 分页查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受参数
        //获取当前页
        String currentPageStr = request.getParameter("currentPage");
        //获取每页显示的条数
        String pageSizeStr = request.getParameter("pageSize");
        //分类数
        String cidStr = request.getParameter("cid");

        String rname = request.getParameter("rname");

        int cid = 0;
        //处理参数
        if(cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)){
             cid = Integer.parseInt(cidStr);
        }
        //当前页码，如果没有传递，则默认显示第一页
        int currentPage = 0;
        if(currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }else{
            currentPage = 1;
        }
        //每页显示条数，如果不传递，默认显示五条
        int pageSize = 0;
        if(pageSizeStr != null && pageSizeStr.length() > 0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else{
            pageSize = 5;
        }


        //调用 service 查询 pageBean 对象
        PageBean<Route> pageBean = routeService.pageQuery(cid, currentPage, pageSize,rname);


        //将pageBean对象转换微微json格式数据返回给客户端
        writeValue(pageBean,response);

    }

    /***
     * 根据id查询一个旅游线路详细信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取id
        String rid = request.getParameter("rid");
        //调用service查询route对象
        Route route = routeService.findOne(rid);
        //将查询的对象返回给客户端
        writeValue(route,response);
    }

    /***
     * 判断当前登录用户是否收藏过改旅游线路
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取线路id
        String rid = request.getParameter("rid");

        //获取当前登录的用户 user
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if(user!=null){
            //当前用户登录
            uid = user.getUid();
        }else{
            uid = 0;
        }

        //调用favoriteService查询是否收藏
        boolean flag = favoriteService.isFavorite(rid, uid);

        writeValue(flag,response);
    }
}
