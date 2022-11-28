package io.bertty.sharable.backend.persistence.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Memory")
public class Memory implements Serializable {

  @Id
  private String id;
  private String questions;
  private String Answer;
  private Date timestamp;
  private List<Comment> comments;

  public Memory() {
  }

  public String getId() {
    return id;
  }

  public Memory setId(String id) {
    this.id = id;
    return this;
  }

  public String getQuestions() {
    return questions;
  }

  public Memory setQuestions(String questions) {
    this.questions = questions;
    return this;
  }

  public String getAnswer() {
    return Answer;
  }

  public Memory setAnswer(String answer) {
    Answer = answer;
    return this;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public Memory setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public Memory setComments(List<Comment> comments) {
    this.comments = comments;
    return this;
  }

  public Memory removeComment(Comment comment){
    if(this.comments == null){
      return this;
    }
    this.comments.remove(comment);
    return this;
  }

  public Memory addComment(Comment comment){
    if(this.comments == null){
      this.comments = new ArrayList<>();
    }
    this.comments.add(comment);
    return this;
  }
}
