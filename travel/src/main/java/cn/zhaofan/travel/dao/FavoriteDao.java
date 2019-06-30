package cn.zhaofan.travel.dao;

import cn.zhaofan.travel.domain.Favorite;

public interface FavoriteDao {
    /**
     * 查询该该用户是否收藏了改线路
     */
    public Favorite isFavorite(int uid, int rid);
    /**
     * 查询该路线有多少人收藏
     */
    public int favoriteCount(int rid);
    /**
     * 添加收藏
     */
    public void add(int rid,int uid);
}
