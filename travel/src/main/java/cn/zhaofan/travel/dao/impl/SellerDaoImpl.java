package cn.zhaofan.travel.dao.impl;

import cn.zhaofan.travel.dao.SellerDao;
import cn.zhaofan.travel.domain.Seller;
import cn.zhaofan.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class SellerDaoImpl implements SellerDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Seller getSellerByRid(int sid) {
        String sql="select * from tab_seller where sid=?";
        Seller seller = template.queryForObject(sql, new BeanPropertyRowMapper<>(Seller.class), sid);
        return seller;
    }
}
