package cn.zhaofan.travel.dao;

import cn.zhaofan.travel.domain.Route;

import java.util.List;

public interface RouteDao {
    /**
     * cid  代表当前分类
     * stat 代表数据开始的位置
     * len  代表发多少条数据
     *
     * @param cid
     * @param stat
     * @param len
     * @return
     */

    public List getRouteByCid(int cid,int stat,int len,String rname);
    public int coutRouteByCid(int cid,String rname);
    /**
     * 根据rid查询一个旅游路线的信息
     */
    public Route getOneRoute(int rid);


}
