package cn.zhaofan.travel.dao.impl;

import cn.zhaofan.travel.dao.UserDao;
import cn.zhaofan.travel.domain.User;
import cn.zhaofan.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public User findUser(String userName) {
        User user = null;
        try {
            String sql="select * from tab_user where username=?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), userName);
        } catch (DataAccessException e) {

        }
        return user;
    }

    @Override
    public int addUser(User user) {
        String sql="insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) values(?,?,?,?,?,?,?,?,?)";
        int update = template.update(sql, user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail(),
                user.getStatus(),
                user.getCode());
        return update;
    }

    @Override
    public User findUserByCode(String code) {
        String sql="select * from tab_user where code=?";
        User user = null;
        try {
            user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), code);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public int updateUserStatus(User user) {
        String sql="update tab_user set status='Y' where uid=?";
        int update = template.update(sql, user.getUid());

        return update;
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        String sql="select * from tab_user where username=? and password=?";
        User user = null;
        try {
            user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username, password);
        } catch (DataAccessException e) {

        }

        return user;
    }
}
