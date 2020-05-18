package com.test.travelnet.service;

import com.test.travelnet.domain.User;

/**
 * @Description
 * @Author Alm
 * @Date 2020/5/16 13:47
 * @Version V1.0
 */
public interface UserService {
    /**
     * 用户注册
     * @param user
     * @return
     */
    Boolean register(User user);

    /**
     * 激活方法
     * @param code
     * @return
     */
    Boolean active(String code);

    /**
     * 登录方法
     * @param user
     * @return
     */
    User login(User user);
}
