package com.test.travelnet.service.impl;

import com.test.travelnet.dao.CategoryDao;
import com.test.travelnet.dao.impl.CategoryDaoImpl;
import com.test.travelnet.domain.Category;
import com.test.travelnet.service.CategoryService;
import com.test.travelnet.utils.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Description
 * @Author Alm
 * @Date 2020/5/17 16:45
 * @Version V1.0
 */
public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {
        //从redis 中查询
        //获取jedis客户端
        Jedis jedis = JedisUtil.getJedis();
        //使用sortedset排序查询
        //Set<String> categorys = jedis.zrange("category", 0, -1);
        //查询sortedset中的cid和cname
        Set<Tuple> categorys = jedis.zrangeWithScores("category", 0, -1);
        List<Category> cs = null;
        if(categorys == null || categorys.size() == 0){
            System.out.println("从数据库中查询");
            //如果为空，需要从数据库查询，然后将数据存储在redis中
            //从数据库中查询
            cs = categoryDao.findAll();

            //将集合数据存储到redis中的category的key中
            for(int i = 0 ;i<cs.size();i++){

                jedis.zadd("category",cs.get(i).getCid(),cs.get(i).getCname());
            }

        }else{
            System.out.println("从redis中查询数据");
            //如果不为空，将set的数据缓存到list中
            cs = new ArrayList<Category>();
            for (Tuple tuple : categorys) {
                Category category = new Category();
                category.setCname(tuple.getElement());
                category.setCid((int)tuple.getScore());
                cs.add(category);

            }
        }
        return cs;
    }
}
