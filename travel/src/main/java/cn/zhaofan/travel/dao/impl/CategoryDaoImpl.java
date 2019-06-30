package cn.zhaofan.travel.dao.impl;

import cn.zhaofan.travel.dao.CategoryDao;
import cn.zhaofan.travel.domain.Category;
import cn.zhaofan.travel.util.JDBCUtils;
import cn.zhaofan.travel.util.JedisUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.List;
import java.util.Set;

public class CategoryDaoImpl implements CategoryDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    private Jedis jedis= JedisUtil.getJedis();
    @Override
    public List<Category> findAll() {
        String sql="select * from tab_category";
        List<Category> query = template.query(sql, new BeanPropertyRowMapper<>(Category.class));
        return query;
    }

    @Override
    public Set<Tuple> findAllByredis(String key) {
        //Set<String> zrange = jedis.zrange(key, 0, -1);
        Set<Tuple> tuples = jedis.zrangeWithScores(key, 0, -1);
        return tuples;
    }

    @Override
    public void addByRedis(String key, int sorte, String value) {
        Long zadd = jedis.zadd(key, sorte, value);
    }

}
