package com.test.travelnet.dao;

import com.test.travelnet.domain.Favorite;

/**
 * @Description
 * @Author Alm
 * @Date 2020/5/24 21:39
 * @Version V1.0
 */
public interface FavoriteDao {


    /**
     * 查询是否收藏线路
     * @param rid
     * @param uid
     * @return
     */
    public Favorite findByRidAndUid(int rid, int uid);

    /**
     * 查询收藏次数
     * @param rid
     * @return
     */
    public int findCountByRid(int rid);

    /***
     * 收藏次数添加
     * @param rid
     * @param uid
     */
    public void add(int rid, int uid);
}
