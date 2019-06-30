package cn.zhaofan.travel.dao;

import java.util.List;
import java.util.Set;

public interface CategoryDao {
    //从数据库中找到所有的categ
    public List findAll();
    public Set findAllByredis(String key);
    public void addByRedis(String key,int sorte,String value);//sorte 分数

}
