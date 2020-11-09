package com.quiraxical.rollingpaper;

import java.util.ArrayList;

public class DAO {
    private DAO instance = new DAO();

    /***
     * mockup part
     * TODO: 데이터베이스 연결 시 이 파트는 삭제할 것
     ***/
    private User user = null;
    private Rollingpaper paper = null;
    private ArrayList<RollingpaperContent> content = null;
    
    private DAO() {
        /***
         * mockup part
         * TODO: 데이터베이스 연결 시 이 파트는 삭제할 것
         ***/
        user = new User();
        paper = new Rollingpaper();
        content = new ArrayList<RollingpaperContent>();
    }

    public DAO getInstance() {
        return instance;
    }
}
