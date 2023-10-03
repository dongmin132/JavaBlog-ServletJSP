package iducs.javaweb.blog201912018.repository;

import java.sql.*;

public class OracleDAOImpl implements DAO {
    @Override
    public Connection getConnection() {
        Connection conn = null;
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE";
        String dbUser = "sw201912018";
        String dbPass = "cometrue";
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    // 자원 회수 : 메모리 누수 발생 방지,
    @Override
    public void closeResources(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs) {
        if (rs != null) try { rs.close(); } catch(Exception e) {}
        if (pstmt != null) try { pstmt.close(); } catch(Exception e) {}
        if (stmt != null) try { stmt.close(); } catch(Exception e) {}
        if (conn != null) try { conn.close(); } catch(Exception e) {}
    }
}
