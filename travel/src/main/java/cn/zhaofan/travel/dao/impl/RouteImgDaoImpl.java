package cn.zhaofan.travel.dao.impl;

import cn.zhaofan.travel.dao.RouteImgDao;
import cn.zhaofan.travel.domain.RouteImg;
import cn.zhaofan.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RouteImgDaoImpl implements RouteImgDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<RouteImg> getImgs(int rid) {
        String sql="select * from tab_route_img where rid=?";
        List<RouteImg> query = template.query(sql, new BeanPropertyRowMapper<>(RouteImg.class), rid);
        return query;
    }
}
