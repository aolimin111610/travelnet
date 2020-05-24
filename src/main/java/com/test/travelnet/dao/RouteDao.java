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
    /**
     * 根据 cid和rname 查询总记录数
     * @param cid
     * @param rname
     * @return
     */
    public int findTotalCount(int cid,String rname);

    /***
     * 根据cid start pageSize查询当前页的数据集合
     * @param cid
     * @param start
     * @param pageSize
     * @param rname
     * @return
     */
    public List<Route> findByPage(int cid, int start, int pageSize,String rname);

    /***
     * 根据id查询
     * @param rid
     * @return
     */
    public Route findOne(int rid);
}
