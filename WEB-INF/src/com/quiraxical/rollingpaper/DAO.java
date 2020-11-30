package com.quiraxical.rollingpaper;

import java.sql.*;
import java.util.ArrayList;
import javax.naming.*;
import javax.sql.DataSource;

public class DAO {
    private static DAO instance = new DAO();
    
    private DAO() {
    }

    private Connection connect() throws NamingException, SQLException {
        Context initContext = new InitialContext();
        Context envContext = (Context)initContext.lookup("java:/comp/env");
        DataSource ds = (DataSource)envContext.lookup("jdbc/mysql");
        return ds.getConnection();
    }

    private void disconnect(Connection conn) throws SQLException {
        if(conn != null) {
            conn.close();
            conn = null;
        }
    }

    public static DAO getInstance() {
        return instance;
    }

    public User findUser(String name, String pwd) throws SQLException, NamingException {
        if(pwd == null || pwd.equals("")) return null;

        Connection conn = connect();
        
        PreparedStatement pstmt = null;

        String sql = "select * from users where u_name=? and u_pwd=?;";

        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,name);
        pstmt.setString(2,pwd);

        ResultSet rs = pstmt.executeQuery();

        if(!rs.next()) {
            return null;
        }

        User user = new User();
        user.setName(name);
        user.setNick(rs.getString("u_nick"));

        rs.close();
        pstmt.close();
        disconnect(conn);

        return user;
    }

    public User createUser(String name, String pwd, String nick) throws NamingException, SQLException {
        User user = new User();
        user.setName(name);
        user.setNick(nick);
        
        if(pwd == null || pwd.equals("")) return null;

        Connection conn = connect();
        
        PreparedStatement pstmt = null;

        String sql = "insert into users(u_name, u_pwd, u_nick) values(?, ?, ?);";

        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,name);
        pstmt.setString(2,pwd);
        pstmt.setString(3,nick);

        pstmt.executeUpdate();

        if(pstmt.getUpdateCount() <= 0) {
            pstmt.close();
            disconnect(conn);
            return null;
        }

        pstmt.close();
        disconnect(conn);
        return user;
    }

    public User updateUser(User user, String pwd) throws SQLException, NamingException {
        if(pwd == null || pwd.equals("")) return null;

        Connection conn = connect();
        
        PreparedStatement pstmt = null;

        String sql = "update users set u_pwd=?, u_nick=? where u_name=?;";

        pstmt = conn.prepareStatement(sql);
        pstmt.setString(3,user.getName());
        pstmt.setString(1,pwd);
        pstmt.setString(2,user.getNick());

        pstmt.executeUpdate();

        if(pstmt.getUpdateCount() <= 0) {
            pstmt.close();
            disconnect(conn);
            return null;
        }

        pstmt.close();
        disconnect(conn);
        return user;
    }

    public boolean deleteUser(User user, String pwd) throws NamingException, SQLException {
        if(pwd == null || pwd.equals("")) return false;

        Connection conn = connect();

        conn.setAutoCommit(false);
        
        PreparedStatement pstmt = null;

        String sql = "delete from rollingpaper_content where r_id in (select r_id from rollingpaper where u_name=?);";

        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,user.getName());

        pstmt.executeUpdate();

        pstmt.close();

        sql = "delete from rollingpaper where u_name=?;";

        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,user.getName());

        pstmt.executeUpdate();

        sql = "delete from users where u_name=? and u_pwd=?;";

        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,user.getName());
        pstmt.setString(2,pwd);

        pstmt.executeUpdate();

        if(pstmt.getUpdateCount() <= 0) {
            pstmt.close();
            conn.rollback();
            disconnect(conn);
            return false;
        }

        pstmt.close();
        conn.commit();
        disconnect(conn);
        return true;
    }

    public ArrayList<Rollingpaper> getRollingpaperLists(User user) throws NamingException, SQLException {
        ArrayList<Rollingpaper> list = new ArrayList<Rollingpaper>();

        Connection conn = connect();
        
        PreparedStatement pstmt = null;

        String sql = "select * from rollingpaper where u_name=?;";

        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,user.getName());

        ResultSet rs = pstmt.executeQuery();

        while(rs.next()) {
            Rollingpaper p = new Rollingpaper();
            p.setId(rs.getInt("r_id"));
            p.setUser(user);
            p.setTo(rs.getString("r_to"));
            p.setTitle(rs.getString("r_title"));
            p.setIsClosed(rs.getBoolean("r_isClosed"));
            list.add(p);
        }

        rs.close();
        pstmt.close();
        disconnect(conn);

        return list;
    }

    public void createRollingpaper(User user, Rollingpaper rp, String pwd) throws NamingException, SQLException {
        if(pwd == null || pwd.equals("")) return;

        Connection conn = connect();
        
        PreparedStatement pstmt = null;

        String sql = "insert into rollingpaper(u_name, r_to, r_title, r_pwd) values(?, ?, ?, ?);";

        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,user.getName());
        pstmt.setString(4,pwd);
        pstmt.setString(2,rp.getTo());
        pstmt.setString(3,rp.getTitle());

        pstmt.executeUpdate();

        pstmt.close();
        disconnect(conn);
    }

    public Rollingpaper getRollingpaper(User user, int id,String pwd) throws NamingException, SQLException {
        if (id == 0) return null;
        
        Connection conn = connect();
        
        PreparedStatement pstmt = null;

        String sql = "select * from rollingpaper where r_id=?;";

        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,id);

        ResultSet rs = pstmt.executeQuery();

        if(!rs.next()) return null;

        if(user == null || !user.getName().equals(rs.getString("u_name"))) {
            if(pwd == null || !pwd.equals(rs.getString("r_pwd"))) return null;
        }

        Rollingpaper p = new Rollingpaper();
        p.setId(rs.getInt("r_id"));
        p.setUser(user);
        p.setTo(rs.getString("r_to"));
        p.setTitle(rs.getString("r_title"));
        p.setIsClosed(rs.getBoolean("r_isClosed"));

        rs.close();
        pstmt.close();
        disconnect(conn);

        return refreshRollingpaper(user, p);
    }

    public Rollingpaper refreshRollingpaper(User user, Rollingpaper rp) throws NamingException, SQLException {
        ArrayList<RollingpaperContent> content = new ArrayList<RollingpaperContent>();

        Connection conn = connect();

        PreparedStatement pstmt = null;

        String sql = "select * from rollingpaper_content where r_id=?;";

        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,rp.getId());

        ResultSet rs = pstmt.executeQuery();

        while(rs.next()) {
            RollingpaperContent rc = new RollingpaperContent();
            rc.setId(rs.getInt("rc_id"));
            rc.setText(rs.getString("rc_text"));
            rc.setFrom(rs.getString("rc_from"));
            content.add(rc);
        }

        rp.setContent(content);

        rs.close();
        pstmt.close();
        disconnect(conn);

        return rp;
    }

    public Rollingpaper changeRollingpaperPassword(User user, Rollingpaper rp, String pwd)
            throws NamingException, SQLException {
        if(pwd == null || pwd.equals("")) return null;

        Connection conn = connect();

        PreparedStatement pstmt = null;

        String sql = "update rollingpaper set r_pwd=? where r_id=? and u_name=?";

        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, pwd);
        pstmt.setInt(2, rp.getId());
        pstmt.setString(3, user.getName());

        pstmt.executeUpdate();

        System.out.println(sql + " / " + pwd + " / " + rp.getId() + " / " + user.getName());

        if(pstmt.getUpdateCount() <= 0) {
            pstmt.close();
            disconnect(conn);
            return null;
        }

        pstmt.close();
        disconnect(conn);
        return rp;
    }

    public boolean closeRollingpaper(User user, Rollingpaper rp) throws NamingException, SQLException {
        return closeRollingpaper(user, rp.getId());
    }

    public boolean closeRollingpaper(User user, int id) throws NamingException, SQLException {
        if (id == 0) return false;
        
        Connection conn = connect();

        PreparedStatement pstmt = null;

        String sql = "update rollingpaper set r_isClosed=true where r_id=? and u_name=?";

        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,id);
        pstmt.setString(2, user.getName());

        pstmt.executeUpdate();

        if(pstmt.getUpdateCount() <= 0) {
            pstmt.close();
            disconnect(conn);
            return false;
        }

        pstmt.close();
        disconnect(conn);
        return true;
    }

    public boolean deleteRollingpaper(User user, int id) throws NamingException, SQLException {
        if (id == 0) return false;

        Connection conn = connect();

        conn.setAutoCommit(false);
        
        PreparedStatement pstmt = null;

        String sql = "delete from rollingpaper_content where r_id=?;";

        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,id);

        pstmt.executeUpdate();

        pstmt.close();

        sql = "delete from rollingpaper where u_name=? and r_id=?;";

        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,user.getName());
        pstmt.setInt(2,id);

        pstmt.executeUpdate();

        if(pstmt.getUpdateCount() <= 0) {
            conn.rollback();
            pstmt.close();
            disconnect(conn);
            return false;
        }

        conn.commit();
        pstmt.close();
        disconnect(conn);
        return true;
    }

    public void createRollingpaperContent(Rollingpaper rp, RollingpaperContent rpc, String pwd)
            throws NamingException, SQLException {
        if(pwd == null || pwd.equals("")) return;

        Connection conn = connect();
        
        PreparedStatement pstmt = null;

        String sql = "select max(rc_id) as rc_id from rollingpaper_content where r_id=?;";

        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, rp.getId());

        ResultSet rs = pstmt.executeQuery();
        
        if(!rs.next()) return;

        int id = rs.getInt("rc_id") + 1;

        rs.close();
        pstmt.close();

        sql = "insert into rollingpaper_content(rc_id, r_id, rc_text, rc_pwd, rc_from) values(?, ?, ?, ?, ?);";

        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,id);
        pstmt.setString(4,pwd);
        pstmt.setInt(2,rp.getId());
        pstmt.setString(3,rpc.getText());
        pstmt.setString(5, rpc.getFrom());

        pstmt.executeUpdate();

        pstmt.close();
        disconnect(conn);
    }
    
    public Rollingpaper deleteRollingpaperContent(User user, Rollingpaper rp, int id, String pwd)
            throws NamingException, SQLException {
        Connection conn = connect();
        
        PreparedStatement pstmt = null;

        String sql = "select * from rollingpaper where r_id=?;";

        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,rp.getId());

        ResultSet rs = pstmt.executeQuery();

        if(!rs.next()) return null;

        boolean isOwner = false;

        sql = "delete from rollingpaper_content where r_id=? and rc_id=?";

        if(user == null || !user.getName().equals(rs.getString("u_name"))) {
            if(pwd == null || pwd.equals("")) return null;

            sql += " and rc_pwd=?;";
            isOwner = false;
        } else isOwner = true;

        rs.close();
        pstmt.close();

        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, rp.getId());
        pstmt.setInt(2,id);
        if(!isOwner) pstmt.setString(3,pwd);

        pstmt.executeUpdate();

        if(pstmt.getUpdateCount() <= 0) {
            pstmt.close();
            disconnect(conn);
            return null;
        }

        pstmt.close();
        disconnect(conn);
        return rp;
    }
}
