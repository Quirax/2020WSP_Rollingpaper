package com.quiraxical.rollingpaper;

import java.util.ArrayList;

public class DAO {
    private static DAO instance = new DAO();

    /***
     * mockup part
     * TODO: 데이터베이스 연결 시 이 파트는 삭제할 것
     ***/
    public User user = null;
    private ArrayList<Rollingpaper> paper = null;
    private ArrayList<RollingpaperContent> content = null;
    
    private DAO() {
        /***
         * mockup part
         * TODO: 데이터베이스 연결 시 이 파트는 삭제할 것
         ***/
        user = new User();
        user.setName("quiraxical");
        user.setNick("킈락");

        paper = new ArrayList<Rollingpaper>();
        for (int i = 1; i <= 5; i++) {
            Rollingpaper rp = new Rollingpaper();
            rp.setId(i);
            rp.setTitle("롤링페이퍼 #" + i);
            rp.setTo("대상자 #" + i * i);
            rp.setUser(user);
            rp.setIsClosed(i % 2 == 0);
            paper.add(rp);
        }

        content = new ArrayList<RollingpaperContent>();
        for (int i = 1; i <= 5; i++) {
            RollingpaperContent rpc = new RollingpaperContent();
            rpc.setId(i);
            rpc.setText("아무말 대잔치 " + i);
            rpc.setFrom("보낸사람 #" + i);
            content.add(rpc);
        }
    }

    public static DAO getInstance() {
        return instance;
    }

    public ArrayList<Rollingpaper> getRollingpaperLists(User user) {
        //TODO: sql로 직접
        return paper;
    }

    public void createRollingpaper(User user, Rollingpaper rp, String pwd) {
        //TODO: sql로 직접
        rp.setId(paper.size() + 1);
        rp.setUser(user);
        paper.add(rp);
    }

    public User findUser(String name, String pwd) {
        //TODO: sql로 직접
        return user;
    }

    public Rollingpaper getRollingpaper(User user, int id,String pwd) {
        if (id == 0) return null;
        //TODO: sql로 직접
        for(Rollingpaper p : paper) {
            if(p.getId() == id) {
                if(!pwd.equals("1234")) return null;
                p.setContent(content);
                return refreshRollingpaper(user, p);
            }
        }
        return null;
    }

    public void createRollingpaperContent(Rollingpaper rp, RollingpaperContent rpc, String pwd) {
        //TODO: sql로 직접
        ArrayList<RollingpaperContent> content = rp.getContent();
        content.add(rpc);
        rp.setContent(content);
    }

    public Rollingpaper refreshRollingpaper(User user, Rollingpaper rp) {
        //TODO: sql로 직접
        //rp.setContent(content);
        return rp;
    }

    public Rollingpaper deleteRollingpaperContent(User user, Rollingpaper rp, int id, String pwd) {
        //TODO: sql로 직접
        ArrayList<RollingpaperContent> content = rp.getContent();
        for(int i = 0; i < content.size(); i++)
            if(content.get(i).getId() == id) {
                if(!pwd.equals("1234")) return null;
                content.remove(i);
                rp.setContent(content);
                return rp;
            }
        return null;
    }
}
