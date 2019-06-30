import cn.zhaofan.travel.dao.impl.CategoryDaoImpl;
import cn.zhaofan.travel.dao.impl.RouteDaoImpl;
import cn.zhaofan.travel.service.impl.RouteServiceImpl;
import cn.zhaofan.travel.util.JDBCUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import redis.clients.jedis.Tuple;

import java.util.Set;

public class test1 {
    @Test
    public void test11(){
        String s="  ";
        System.out.println(s==null);
    }

    @Test
    public void test2(){
        CategoryDaoImpl impl=new CategoryDaoImpl();
        Set<Tuple> category = impl.findAllByredis("category");

    }

    @Test
    public void test3(){
        double ceil = Math.ceil(5 / 2.0);
        System.out.println(ceil);
    }
    @Test
    public void test4(){
        String sql="select count(*) from tab_route where cid=?";
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        int inte= template.queryForObject(sql, new BeanPropertyRowMapper<>(Integer.class), 5);
        System.out.println(inte+"erw");

//        RouteDaoImpl route=new RouteDaoImpl();
//        route.coutRouteByCid(5);


    }

    @Test
    public void test5(){
        RouteServiceImpl route=new RouteServiceImpl();
        //List<Route> routeByCid = route.getRouteByCid(5, 0, 6);
        //System.out.println(routeByCid.size());
    }

    @Test
    public void test6(){
        RouteDaoImpl route=new RouteDaoImpl();
        /*List<Route> aa = route.getRouteByCid(5, 0, 8, "西安");
        for(Route route1:aa){
            System.out.println(route1+"qwwq");

        }*/
        int xian = route.coutRouteByCid(5, "西安");
        System.out.println(xian+"wer");
    }
}
