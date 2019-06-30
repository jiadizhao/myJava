package cn.zhaofan.travel.domain;

import java.util.List;

/**
 * 作为给客户端返回的json数据,将数据总条数,当前页码,总页数,,和当前分类下的数据List集合
 */
public class Page {
    private int currentPage;
    private int dataCount;
    private int toltalPage;
    private List<Route> routes;
    private int dataSize;//一行显示几条
    private int cid;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getDataCount() {
        return dataCount;
    }

    public void setDataCount(int dataCount) {
        this.dataCount = dataCount;
    }

    public int getToltalPage() {
        return toltalPage;
    }

    public void setToltalPage(int toltalPage) {
        this.toltalPage = toltalPage;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public int getDataSize() {
        return dataSize;
    }

    public void setDataSize(int dataSize) {
        this.dataSize = dataSize;
    }
}
