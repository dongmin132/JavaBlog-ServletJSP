package iducs.javaweb.blog201912018.repository;

import iducs.javaweb.blog201912018.model.Blog;

import java.util.List;

public interface BlogDAO {
    int create(Blog blog);
    Blog read(Blog blog);
    List<Blog> readList();
    int update(Blog blog);
    int delete(Blog blog);

}
