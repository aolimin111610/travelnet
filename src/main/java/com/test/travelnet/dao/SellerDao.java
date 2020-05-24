package com.test.travelnet.dao;

import com.test.travelnet.domain.Seller;

/**
 * @Description 商家信息接口
 * @Author Alm
 * @Date 2020/5/24 19:57
 * @Version V1.0
 */
public interface SellerDao {
    /**
     * 通过id查询商家信息
     * @param sid
     * @return
     */
    public Seller findBySid(int sid);
}
