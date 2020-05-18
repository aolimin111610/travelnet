package com.test.travelnet.service.impl;

import com.test.travelnet.dao.UserDao;
import com.test.travelnet.dao.impl.UserDaoImpl;
import com.test.travelnet.domain.User;
import com.test.travelnet.service.UserService;
import com.test.travelnet.utils.MailUtils;
import com.test.travelnet.utils.UuidUtil;

/**
 * @Description
 * @Author Alm
 * @Date 2020/5/16 13:52
 * @Version V1.0
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public Boolean register(User user) {
        User u = userDao.findByuserName(user.getUsername());
        if(u != null){
          //用户存在
            return false;
        }
        //用户不存在
        user.setCode(UuidUtil.getUuid());
        //未激活状态
        user.setStatus("N");
        userDao.save(user);

        //发送邮件
        String content="<a href='http://localhost:8080/user/active?code="+user.getCode()+"'>点击激活【旅游网】</a>";
        MailUtils.sendMail(user.getEmail(),content,"激活邮件");
        return true;
    }

    @Override
    public Boolean active(String code) {
        User user = userDao.findByCode(code);
        if(user != null){
            userDao.updateStatus(user);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public User login(User user) {
        return  userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
