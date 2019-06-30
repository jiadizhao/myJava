package cn.zhaofan.travel.web.servlet;

import cn.zhaofan.travel.domain.Page;
import cn.zhaofan.travel.domain.Route;
import cn.zhaofan.travel.domain.User;
import cn.zhaofan.travel.service.FavoriteService;
import cn.zhaofan.travel.service.RouteService;
import cn.zhaofan.travel.service.impl.FavoriteServiceImpl;
import cn.zhaofan.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    private RouteService routeService=new RouteServiceImpl();
    private FavoriteService favoriteService=new FavoriteServiceImpl();
    public void reoutByCid(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String rname=request.getParameter("rname");
        String cidStr = request.getParameter("cid")==null?"5":request.getParameter("cid");
        String currentPageStr = request.getParameter("currentPage");//当前页
        String dataSizeStr=request.getParameter("dataSize");//一页显示的数据个数
        //判断传回的参数是否为空
        if("".equals(cidStr)||cidStr.length()==0||"null".equals(cidStr)){
            cidStr="0";
        }
        if(currentPageStr==null||currentPageStr.length()==0){
            currentPageStr="1";
        }
        Page page=new Page();
        int currentPage=Integer.parseInt(currentPageStr);
        if(currentPage<=0){
            currentPage=1;
        }
        int dataSize= Integer.parseInt(dataSizeStr);
        int cid=Integer.parseInt(cidStr);
        int routeCount = routeService.routeCount(cid,rname);
        int totalPage= (int) Math.ceil(routeCount/(double)dataSize);
        page.setCid(cid);
        page.setDataSize(dataSize);
        page.setDataCount(routeCount);
        page.setToltalPage(totalPage);
        //从数据库中查询数据,起始位置=(当前页码-1)*显示数据个数
        List<Route> routeByCid = routeService.getRouteByCid(cid, (currentPage - 1) * dataSize, dataSize,rname);
        page.setRoutes(routeByCid);
        writeValue(page,response);
    }

    //查询单个旅游路线
    public void findOne(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String ridStr = request.getParameter("rid");
        int rid=Integer.parseInt(ridStr);
        Route route=routeService.findOne(rid);
        writeValue(route,response);
    }

    //判断是否已收藏
    public void isFavorite(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String ridStr = request.getParameter("rid");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int uid;
        boolean b=false;
        if(user==null){
            b=false;
        }else{
            uid=user.getUid();
            b=favoriteService.isFavorite(uid,Integer.parseInt(ridStr));
        }
        writeValue(b,response);
    }

    //添加收藏项
    public void addFavorite(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String rid = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("user");
        favoriteService.add(Integer.parseInt(rid),user.getUid());
    }

    public void isLogin(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user==null){
            writeValue(false,response);
        }else{
            writeValue(true,response);
        }
    }

}
