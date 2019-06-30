package cn.zhaofan.travel.dao.impl;

import cn.zhaofan.travel.dao.RouteDao;
import cn.zhaofan.travel.domain.Route;
import cn.zhaofan.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Route> getRouteByCid(int cid, int stat, int len,String rname) {
        String sql="select * from tab_route where 1=1 ";
        StringBuffer sb=new StringBuffer(sql);
        List list=new ArrayList();
        if(cid!=0){
            sb.append(" and cid=? ");
            list.add(cid);
        }
        if(rname!=null&&rname.length()!=0&&!"null".equals(rname)){
            sb.append(" and rname like ? ");
            list.add("%"+rname+"%");
        }
        sb.append(" limit ?,?");
        list.add(stat);
        list.add(len);
        sql=sb.toString();
        List<Route> query = template.query(sql, new BeanPropertyRowMapper<>(Route.class),list.toArray());
        return query;
    }

    @Override
    public int coutRouteByCid(int cid,String rname) {
        String sql="select count(*) from tab_route where 1=1 ";
        StringBuffer sb=new StringBuffer(sql);
        List list=new ArrayList();
        int count = 0;
        if(cid!=0){
            sb.append(" and cid=? ");
            list.add(cid);
        }
        if(rname!=null&&rname.length()!=0&&!"null".equals(rname)){
            sb.append(" and rname like ? ");
            list.add("%"+rname+"%");
        }
        sql=sb.toString();
        try {
            count = template.queryForObject(sql,Integer.class,list.toArray());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public Route getOneRoute(int rid) {
        String sql="select * from tab_route where rid=?";
        Route route = template.queryForObject(sql, new BeanPropertyRowMapper<Route>(Route.class), rid);
        return route;
    }
}
