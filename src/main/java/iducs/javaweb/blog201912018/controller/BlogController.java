package iducs.javaweb.blog201912018.controller;

import iducs.javaweb.blog201912018.model.Blog;
import iducs.javaweb.blog201912018.model.Member;
import iducs.javaweb.blog201912018.repository.BlogDAO;
import iducs.javaweb.blog201912018.repository.BlogDAOImpl;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BlogController", urlPatterns = { "/blogs/post-form.do", "/blogs/post.do",
        "/blogs/list.do", "/blogs/detail.do", "/blogs/read.do",
        "/blogs/updateForm.do", "/blogs/update.do", "/blogs/delete.do"})
public class BlogController extends HttpServlet {

    BlogDAO blogDAOImpl = new BlogDAOImpl();
    Blog blog = null;

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // request 객체의 인코딩을 설정, ISO-8859-1 기본
        HttpSession session = request.getSession(); // 세션 객체를 가져옮

        String uri = request.getRequestURI();
        String action = uri.substring(uri.lastIndexOf('/') + 1);
        if (action.equals("post-form.do")) {

            Member member = new Member();
            member.setEmail((String) session.getAttribute("logined"));
            member.setName((String) session.getAttribute("name"));
            System.out.println("이름" + member.getName());
            System.out.println(member.getEmail());

            request.setAttribute("loginedEmail", member.getEmail()); //email로 조회한 회원 정보 객체를 member라는 키로 request에 attribute로 저장
            request.setAttribute("loginedName", member.getName());

            request.getRequestDispatcher("../blogs/blog-post-form.jsp").forward(request, response);

        }

        else if (action.equals("post.do")) {

            Blog blog = new Blog();
            blog.setAuthor(request.getParameter("author"));
            blog.setEmail(request.getParameter("email"));
            blog.setContent(request.getParameter("content"));  //MemberController 73번째 줄
            blog.setTitle(request.getParameter("title"));
            System.out.println(blog);
            if(blogDAOImpl.create(blog) > 0 ) {
                request.setAttribute("blog", blog); //email로 조회한 회원 정보 객체를 member라는 키로 request에 attribute로 저장
                request.setAttribute("message", "블로그 작성 성공");

                request.getRequestDispatcher("../status/success.jsp").forward(request, response);
            }
            else
                request.setAttribute("message", "message Fail!");
            request.getRequestDispatcher("../status/fail.jsp").forward(request, response);
        }

        else if(action.equals("list.do")) {
            List<Blog> blogList = new ArrayList<Blog>();
            if((blogList = blogDAOImpl.readList()) != null) {
                request.setAttribute("blogs", blogList);
                request.getRequestDispatcher("../blogs/list-view.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "블로그 목록 가져오기 오류 - 불편을 드려 죄송합니다.");
                request.getRequestDispatcher("../status/fail.jsp").forward(request, response);
            }
        }
        else if (action.equals("detail.do")){
            System.out.println("여기로는 들어와지는거야?");
            Blog retBlog = null;
            blog = new Blog();
            blog.setId(Long.parseLong(request.getParameter("id")));
            System.out.println(blog.getId());
            if ((retBlog = blogDAOImpl.read(blog)) != null) {
                request.setAttribute("blog", retBlog);
                request.getRequestDispatcher("read-view.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "블로그 읽기 오류 - 불편을 드려 죄송합니다. 쿼리 파라미터가 동작하지 않습니다");
                request.getRequestDispatcher("../error/message.jsp").forward(request, response);
            }
        }
        else if(action.equals("update.do")){
            int ret = 0;
            Blog blog = new Blog();
            blog.setId(Long.parseLong(request.getParameter("id")));
            blog.setAuthor(request.getParameter("author"));
            blog.setEmail(request.getParameter("email"));
            blog.setTitle(request.getParameter("title"));
            blog.setContent(request.getParameter("content"));

            if((ret = blogDAOImpl.update(blog)) > 0){
                request.setAttribute("blog", blog);
                request.getRequestDispatcher("../blogs/read-view.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "블로그 수정에 실패");
                request.getRequestDispatcher("../status/fail.jsp").forward(request, response);
            }
        }
        else if(action.equals("delete.do")){
            int ret = 0;
            Blog blog = new Blog();
            blog.setId(Long.parseLong(request.getParameter("id")));

            if((ret = blogDAOImpl.delete(blog)) > 0){
                session.invalidate();
                request.setAttribute("message", "blog delete success");
                request.getRequestDispatcher("../status/success.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "회원 탈퇴 실패");
                request.getRequestDispatcher("../status/fail.jsp").forward(request, response);
            }

        }


    }




    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }


}
