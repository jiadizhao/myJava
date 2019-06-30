package cn.zhaofan.travel.service.impl;

import cn.zhaofan.travel.dao.CategoryDao;
import cn.zhaofan.travel.dao.impl.CategoryDaoImpl;
import cn.zhaofan.travel.domain.Category;
import cn.zhaofan.travel.service.CategoryService;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao=new CategoryDaoImpl();
    @Override
    public List findCategory() {
        //查询所有的分类条目
        //1.从redis中查询数据
        String s="category";
        Set<Tuple> category = categoryDao.findAllByredis("category");
        List<Category> list=new ArrayList();
        //2.1有数据,将数据转化成list集合,返回
        if(category!=null&&category.size()!=0){
            for(Tuple tuple:category){
                Category category1 = new Category();
                category1.setCname(tuple.getElement());
                category1.setCid((int) tuple.getScore());
                list.add(category1);
            }
        }else{
            //2.2没有数据,将数据从数据库中查询
            list= categoryDao.findAll();
            //2.3添加到redis中,并返回
            for(Category cate:list){
                categoryDao.addByRedis ("category",cate.getCid(),cate.getCname());
            }

        }
        return list;
    }
}
