package com.test.travelnet.service.impl;

import com.test.travelnet.dao.CategoryDao;
import com.test.travelnet.dao.FavoriteDao;
import com.test.travelnet.dao.impl.CategoryDaoImpl;
import com.test.travelnet.dao.impl.FavoriteDaoImpl;
import com.test.travelnet.domain.Favorite;
import com.test.travelnet.service.FavoriteService;

/**
 * @Description
 * @Author Alm
 * @Date 2020/5/24 21:38
 * @Version V1.0
 */
public class FavoriteServiceImpl implements FavoriteService {

    private FavoriteDao favoriteDao = new FavoriteDaoImpl();

    @Override
    public boolean isFavorite(String rid,int uid) {
        Favorite favorite = favoriteDao.findByRidAndUid(Integer.parseInt(rid),uid);

        return favorite !=null;
    }

    @Override
    public void add(String rid, int uid) {
        favoriteDao.add(Integer.parseInt(rid),uid);
    }
}
