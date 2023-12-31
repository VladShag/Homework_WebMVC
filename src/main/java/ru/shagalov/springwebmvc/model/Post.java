package ru.shagalov.springwebmvc.model;

public class Post {
  private long id;
  private String content;
  private boolean isDeleted;

  public Post() {
  }

  public Post(long id, String content) {
    this.id = id;
    this.content = content;
    isDeleted = false;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public boolean isDeleted() {
    return isDeleted;
  }

  public void setDeleted(boolean deleted) {
    isDeleted = deleted;
  }
}
