package cn.zhaofan.travel.service;

import cn.zhaofan.travel.domain.User;

public interface UserService {
    public boolean registerUsesr(User user);
    public boolean active(String code);

    public User loginUserService(User user);
}
