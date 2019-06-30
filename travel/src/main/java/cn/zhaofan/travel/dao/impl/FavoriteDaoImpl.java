package cn.zhaofan.travel.dao.impl;

import cn.zhaofan.travel.dao.FavoriteDao;
import cn.zhaofan.travel.domain.Favorite;
import cn.zhaofan.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

public class FavoriteDaoImpl implements FavoriteDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Favorite isFavorite(int uid, int rid) {
        String sql="select * from tab_favorite where uid=? and rid=?";
        Favorite query = null;
        try {
            query = template.queryForObject(sql, new BeanPropertyRowMapper<>(Favorite.class), uid, rid);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return query;
    }

    @Override
    public int favoriteCount(int rid) {
        String sql="select count(*) from tab_favorite where rid=?";
        Integer integer = 0;
        try {
            integer = template.queryForObject(sql, Integer.class, rid);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return integer;
    }

    @Override
    public void add(int rid, int uid) {
        String sql="insert into tab_favorite values(?,?,?)";
        int update = template.update(sql, rid, new Date(), uid);
    }
}
