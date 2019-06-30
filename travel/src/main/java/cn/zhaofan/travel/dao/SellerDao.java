package cn.zhaofan.travel.dao;

import cn.zhaofan.travel.domain.Seller;

public interface SellerDao {
    /**
     * 通过rid获取商家信息
     */
    public Seller getSellerByRid(int sid);
}
