package cn.zhaofan.travel.dao;

import cn.zhaofan.travel.domain.RouteImg;

import java.util.List;

public interface RouteImgDao {
    /**
     * 根据rid来查询图片集
     */
    public List<RouteImg> getImgs(int rid);
}
