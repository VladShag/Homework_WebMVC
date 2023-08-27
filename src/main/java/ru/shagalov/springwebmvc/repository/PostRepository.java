package ru.shagalov.springwebmvc.repository;

import ru.shagalov.springwebmvc.exception.NotFoundException;
import ru.shagalov.springwebmvc.model.Post;
import org.springframework.stereotype.Repository;


import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

// Stub
@Repository
public class PostRepository {
  private ConcurrentHashMap<Long, Post> repo;
  private AtomicLong idCounter;

  public PostRepository() {
    idCounter = new AtomicLong();
    idCounter.set(1);
    this.initRepo();
  }
  private ConcurrentHashMap<Long, Post> initRepo() {
    if(this.repo != null) return repo;
    else {
      this.repo = new ConcurrentHashMap<>();
    }
    return repo;
  }

  public List<Post> all() {
    List<Post> allPosts = repo.values().stream().filter(p -> !p.isDeleted()).collect(Collectors.toList());
    return allPosts;
  }

  public Post getById(long id) {
    if(idCounter.get() < id || repo.get(id).isDeleted()) {
      throw new NotFoundException("No post with id " + id + " is exist!");
    } else {
      return repo.get(id);
    }
  }

  public Post save(Post post) {
    if(post.getId() == 0) {
      Long ident = idCounter.getAndIncrement();
      post.setId(ident);
      post.setDeleted(false);
      repo.put(ident, post);
    } else if (this.getById(post.getId()) != null && !this.getById(post.getId()).isDeleted()) {
        Post postToEdit = repo.get(post.getId());
        postToEdit.setContent(post.getContent());
    } else {
      throw new NotFoundException("No post with id " + post.getId() + " is exist! To save new please change id to 0");
    }
    return post;
  }

  public void removeById(long id) {
    if(this.getById(id) != null) {
      repo.get(id).setDeleted(true);
    } else {
      throw new NotFoundException("No post with id " + id + " is exist!");
    }
  }
}
