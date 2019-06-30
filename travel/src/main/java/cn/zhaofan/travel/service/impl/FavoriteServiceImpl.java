package cn.zhaofan.travel.service.impl;

import cn.zhaofan.travel.dao.FavoriteDao;
import cn.zhaofan.travel.dao.impl.FavoriteDaoImpl;
import cn.zhaofan.travel.domain.Favorite;
import cn.zhaofan.travel.service.FavoriteService;

public class FavoriteServiceImpl implements FavoriteService {
    private FavoriteDao favoriteDao=new FavoriteDaoImpl();
    @Override
    public boolean isFavorite(int uid, int rid) {
        Favorite favorite = favoriteDao.isFavorite(uid, rid);
        if(favorite==null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public int favoriteCount(int rid) {
        int i = favoriteDao.favoriteCount(rid);
        return i;
    }

    @Override
    public void add(int rid, int uid) {
        favoriteDao.add(rid,uid);//注意:要按照rid uid的顺序
    }
}
