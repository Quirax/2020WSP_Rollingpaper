package com.quiraxical.rollingpaper;

import java.util.ArrayList;

public class DAO {
    private static DAO instance = new DAO();

    /***
     * mockup part
     * TODO: 데이터베이스 연결 시 이 파트는 삭제할 것
     ***/
    public ArrayList<User> user = null;
    private ArrayList<Rollingpaper> paper = null;
    private ArrayList<RollingpaperContent> content = null;
    
    private DAO() {
        /***
         * mockup part
         * TODO: 데이터베이스 연결 시 이 파트는 삭제할 것
         ***/
        user = new ArrayList<User>();
        User usr = new User();
        usr.setName("quirax");
        usr.setNick("킈락");
        user.add(usr);

        paper = new ArrayList<Rollingpaper>();
        for (int i = 1; i <= 5; i++) {
            Rollingpaper rp = new Rollingpaper();
            rp.setId(i);
            rp.setTitle("롤링페이퍼 #" + i);
            rp.setTo("대상자 #" + i * i);
            rp.setUser(usr);
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

    public User findUser(String name, String pwd) {
        //TODO: sql로 직접
        for(User u : user) {
            if(u.getName().equals(name)) {
                if(pwd == null || !pwd.equals("1234")) return null;
                return u;
            }
        }
        return null;
    }

    public User createUser(String name, String pwd, String nick) {
        User usr = new User();
        for(User u : user)
            if(u.getName().equals(name)) return null;
        usr.setName(name);
        usr.setNick(nick);
        user.add(usr);
        return usr;
    }

    public ArrayList<Rollingpaper> getRollingpaperLists(User user) {
        //TODO: sql로 직접
        ArrayList<Rollingpaper> list = new ArrayList<Rollingpaper>();
        for(Rollingpaper r : paper) {
            if(r.getUser().getName().equals(user.getName())) list.add(r);
        }
        return list;
    }

    public void createRollingpaper(User user, Rollingpaper rp, String pwd) {
        //TODO: sql로 직접
        rp.setId(paper.size() + 1);
        rp.setUser(user);
        paper.add(rp);
    }

    public Rollingpaper getRollingpaper(User user, int id,String pwd) {
        if (id == 0) return null;
        //TODO: sql로 직접
        for(Rollingpaper p : paper) {
            if(p.getId() == id) {
                if(user == null || !user.getName().equals(p.getUser().getName())) {
                    if(pwd == null || !pwd.equals("1234")) return null;
                }
                p.setContent(content);
                return refreshRollingpaper(user, p);
            }
        }
        return null;
    }

    public Rollingpaper refreshRollingpaper(User user, Rollingpaper rp) {
        //TODO: sql로 직접
        //if(user != null && !user.getName().equals("") && !user.getName().equals(rp.getUser().getName())) return null;
        //rp.setContent(content);
        return rp;
    }

    public Rollingpaper changeRollingpaperPassword(User user, Rollingpaper rp, String pwd) {
        //TODO: sql로 직접
        if(user == null || !user.getName().equals(rp.getUser().getName())) return null;
        return rp;
    }

    public Rollingpaper closeRollingpaper(User user, Rollingpaper rp) {
        if(user == null || !user.getName().equals(rp.getUser().getName())) return null;
        rp.setIsClosed(true);
        return rp;
    }

    public Rollingpaper closeRollingpaper(User user, int id) {
        if (id == 0) return null;
        //TODO: sql로 직접
        for(Rollingpaper p : paper) {
            if(p.getId() == id) {
                if(user == null || !user.getName().equals(p.getUser().getName())) return null;
                p.setIsClosed(true);
                return p;
            }
        }
        return null;
    }

    public boolean deleteRollingpaper(User user, int id) {
        if (id == 0) return false;
        //TODO: sql로 직접
        for(int i = 0; i < paper.size(); i++) {
            if(paper.get(i).getId() == id) {
                if(user == null || !user.getName().equals(paper.get(i).getUser().getName())) return false;
                paper.remove(i);
                return true;
            }
        }
        return false;
    }

    public void createRollingpaperContent(Rollingpaper rp, RollingpaperContent rpc, String pwd) {
        //TODO: sql로 직접
        ArrayList<RollingpaperContent> content = rp.getContent();
        content.add(rpc);
        rp.setContent(content);
    }
    
    public Rollingpaper deleteRollingpaperContent(User user, Rollingpaper rp, int id, String pwd) {
        //TODO: sql로 직접
        ArrayList<RollingpaperContent> content = rp.getContent();
        for(int i = 0; i < content.size(); i++)
            if(content.get(i).getId() == id) {
                if(user == null || !user.getName().equals(rp.getUser().getName())) {
                    if(pwd == null || !pwd.equals("1234")) return null;
                }
                content.remove(i);
                rp.setContent(content);
                return rp;
            }
        return null;
    }
}
