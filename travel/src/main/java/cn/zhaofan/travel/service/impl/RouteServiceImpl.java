package cn.zhaofan.travel.service.impl;

import cn.zhaofan.travel.dao.FavoriteDao;
import cn.zhaofan.travel.dao.RouteDao;
import cn.zhaofan.travel.dao.RouteImgDao;
import cn.zhaofan.travel.dao.SellerDao;
import cn.zhaofan.travel.dao.impl.FavoriteDaoImpl;
import cn.zhaofan.travel.dao.impl.RouteDaoImpl;
import cn.zhaofan.travel.dao.impl.RouteImgDaoImpl;
import cn.zhaofan.travel.dao.impl.SellerDaoImpl;
import cn.zhaofan.travel.domain.Route;
import cn.zhaofan.travel.domain.RouteImg;
import cn.zhaofan.travel.domain.Seller;
import cn.zhaofan.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    private RouteDao routeDao=new RouteDaoImpl();
    private RouteImgDao routeImgDao=new RouteImgDaoImpl();
    private SellerDao sellerDao=new SellerDaoImpl();
    private FavoriteDao favoriteDao=new FavoriteDaoImpl();

    //判断数据条数,如果在前200条,则放入redis数据库当中
    @Override
    public List<Route> getRouteByCid(int cid , int stat, int size,String rname) {
        List routeByCid = routeDao.getRouteByCid(cid, stat, size,rname);
        return routeByCid;
    }

    @Override
    public int routeCount(int cid,String rname) {
        int i = routeDao.coutRouteByCid(cid,rname);
        return i;
    }

    @Override
    public Route findOne(int rid) {
        Route route = routeDao.getOneRoute(rid);
        List<RouteImg> imgs = routeImgDao.getImgs(rid);
        route.setRouteImgList(imgs);
        Seller sellerByRid = sellerDao.getSellerByRid(route.getSid());
        route.setSeller(sellerByRid);
        route.setCount(favoriteDao.favoriteCount(rid));
        return route;
    }

}
