package com.test.travelnet.dao;

import com.test.travelnet.domain.RouteImg;

import java.util.List;

/**
 * @Description
 * @Author Alm
 * @Date 2020/5/24 19:39
 * @Version V1.0
 */
public interface RouteImgDao {
    /***
     * 通过routeId查询routeImg图片数据
     * @param id
     * @return
     */
    public List<RouteImg> findByRouteId(int id);

}
