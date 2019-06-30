package cn.zhaofan.travel.dao;

import cn.zhaofan.travel.domain.User;

public interface UserDao {
    public User findUser(String userName);
    public int addUser(User user);
    public User findUserByCode(String code);
    public int updateUserStatus(User user);

    public User findUserByUsernameAndPassword(String username, String password);


}
