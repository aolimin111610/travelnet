package com.test.travelnet.service;

import com.test.travelnet.domain.PageBean;
import com.test.travelnet.domain.Route;

/**
 * @Description
 * @Author Alm
 * @Date 2020/5/20 22:08
 * @Version V1.0
 */
public interface RouteService {
    /***
     * 根据类别进行分页查询
     * @param cid
     * @param currentPage
     * @param pageSize
     * @param pageSize
     * @return
     */
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize,String rname);

    /***
     * 根据rid查询旅游线路的数据
     * @param rid
     * @return
     */
    Route findOne(String rid);
}
