package iducs.javaweb.blog201912018.repository;

import iducs.javaweb.blog201912018.model.Blog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BlogDAOImpl extends OracleDAOImpl implements BlogDAO {
    private Connection conn;
    private Statement stmt;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public BlogDAOImpl() {

    }


    @Override
    public int create(Blog blog) {
        String sql = "insert into b201912018 values(seq_blog201912018.nextval, ?, ?, ?, ?, ?)";
        int rows = 0; // 질의 처리 결과 영향받은 행의 수
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, blog.getTitle());
            pstmt.setString(2, blog.getContent());
            pstmt.setString(3, blog.getAuthor());
            pstmt.setString(4, blog.getEmail());
            pstmt.setString(5, blog.getRegDate());
            rows = pstmt.executeUpdate();// 1 이상이면 정상, 0 이하면 오류
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }


    @Override
    public List<Blog> readList() {
        List<Blog> blogList = null;
        Blog retBlog = null;
        String sql = "select * from b201912018 order by regdate desc";
        try {
            conn = getConnection(); // DB 연결 객체 생성
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql); // SQL 실행 후 결과를 rs에 반환
            blogList = new ArrayList<Blog>();
            while (rs.next()) { // 다음 record값을 접근
                // rs : record 집합, rs.getString(1) : 현재 레코드의 첫번재 필드 값
                retBlog = new Blog();
                retBlog.setId(rs.getInt("id"));
                retBlog.setTitle(rs.getString("title")); // 생성 시 필드 순서
                retBlog.setContent(rs.getString("content"));
                retBlog.setAuthor(rs.getString("author"));
                retBlog.setEmail(rs.getString("email"));
                retBlog.setRegDate(rs.getString("regDate"));


                blogList.add(retBlog);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeResources(conn, stmt, pstmt, rs);
            return blogList;
        }

    }


   @Override
    public int update(Blog blog) {
        int rows = 0;
        String sql = "update b201912018 set author=?, email=?, title=?, content=?, regDate=? where id='" + blog.getId() + "'";
        System.out.println(sql);
        try {
            conn = getConnection(); // DB 연결 객체 생성
            pstmt = conn.prepareStatement(sql);
            System.out.println("여기 위에서 종료된다.");
            pstmt.setString(1, blog.getAuthor());
            pstmt.setString(2, blog.getEmail());
            pstmt.setString(3, blog.getTitle());
            pstmt.setString(4, blog.getContent());
            pstmt.setString(5, blog.getRegDate());
            rows = pstmt.executeUpdate();
            if(rows > 0) {
                rows = 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, pstmt, rs);
        }
        return rows;
    }

   @Override
   public Blog read(Blog blog) {
       Blog ret = null;
       String sql = "select * from b201912018 where id='" + blog.getId() + "'";
       System.out.println(sql);
       try {
           conn = getConnection();
           stmt = conn.createStatement();
           rs = stmt.executeQuery(sql); // SQL 실행 후 결과를 rs에 반환
           if(rs.next()) { // 결과 집합에서 다음 결과가 있는 지 확인, 있으면 true
               ret = new Blog();
               ret.setId(rs.getLong("id"));
               ret.setAuthor(rs.getString("author"));
               ret.setEmail(rs.getString("email"));
               ret.setTitle(rs.getString("title"));
               ret.setContent(rs.getString("content"));
               System.out.println("여기까지 잘 들어와지는지 확인");
               ret.setRegDate(rs.getString("regDate"));
           }
       } catch(SQLException e) {
           System.out.println(e.getMessage());
       } finally {
           closeResources(conn, stmt, pstmt, rs);
           return ret;
       }
   }



    @Override
    public int delete(Blog blog) {
        int rows = 0;
        String sql = "delete from b201912018 where id=?";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, blog.getId());
            rows = pstmt.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

}



