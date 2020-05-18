package com.test.travelnet.dao;

import com.test.travelnet.domain.User;

/**
 * @Description
 * @Author Alm
 * @Date 2020/5/16 13:37
 * @Version V1.0
 */
public interface UserDao {
    /***
     *  通过用户名查询用户信息
     * @param username
     * @return
     */
    public User findByuserName(String username);

    /**
     *  保存用户
     * @param user
     */
    public void save(User user);

    /**
     * 更新账号状态
     * @param user
     */
    public void updateStatus(User user);

    /**
     * 查询账号状态
     * @param code
     * @return
     */
    public User findByCode(String code);

    /***
     *  通过用户名和密码查询用户
     * @param username
     * @param password
     * @return
     */
    public User findByUsernameAndPassword(String username,String password);
}
