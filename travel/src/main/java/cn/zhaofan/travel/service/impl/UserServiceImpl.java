package cn.zhaofan.travel.service.impl;

import cn.zhaofan.travel.dao.UserDao;
import cn.zhaofan.travel.dao.impl.UserDaoImpl;
import cn.zhaofan.travel.domain.User;
import cn.zhaofan.travel.service.UserService;
import cn.zhaofan.travel.util.MailUtils;
import cn.zhaofan.travel.util.UuidUtil;

public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();
    @Override
    public boolean registerUsesr(User user) {
        User u = userDao.findUser(user.getUsername());
        if(u==null){
            user.setStatus("N");
            user.setCode(UuidUtil.getUuid());//使用Uuid生成全球唯一的字符串
            userDao.addUser(user);
            MailUtils.sendMail(user.getEmail(),"<a href='http://localhost/travel/user/activeUser?code="+user.getCode()+"'>【点击确认注册】</a>","确认注册");
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean active(String code) {
        User user = userDao.findUserByCode(code);
        if (user == null) {
            return false;
        }else{
            userDao.updateUserStatus(user);//修改status
        }
        return true;
    }

    @Override
    public User loginUserService(User user) {
        User u= userDao.findUserByUsernameAndPassword(user.getUsername(),user.getPassword());
        return u;
    }

}
