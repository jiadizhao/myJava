package cn.zhaofan.travel.service;

import cn.zhaofan.travel.domain.Route;

import java.util.List;

public interface RouteService {
    public List<Route> getRouteByCid(int cid , int stat, int size,String rname);
    public int routeCount(int cid,String rname);

    /**
     * 将一条旅游路线的的详情,商家信息和图片信息展示出来
     * @param rid
     * @return
     */
    public Route findOne(int rid);



}
