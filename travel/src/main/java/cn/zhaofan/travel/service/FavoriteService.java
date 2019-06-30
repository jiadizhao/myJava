package cn.zhaofan.travel.service;

public interface FavoriteService {
    /**
     * 查询该用户下改线路是否被收藏了
     * @param uid
     * @param rid
     * @return
     */
    public boolean isFavorite(int uid,int rid);
    public int favoriteCount(int rid);
    public void add(int rid,int uid);
}
