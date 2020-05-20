package com.test.travelnet.dao;

import com.test.travelnet.domain.Route;

import java.util.List;

/**
 * @Description
 * @Author Alm
 * @Date 2020/5/20 22:12
 * @Version V1.0
 */
public interface RouteDao {
    /***
     * 根据 cid 查询总记录数
     * @param cid
     * @return
     */
    public int findTotalCount(int cid);

    /***
     * 根据cid start pageSize查询当前页的数据集合
     * @param cid
     * @param start
     * @param pageSize
     * @return
     */
    public List<Route> findByPage(int cid, int start, int pageSize);
}
