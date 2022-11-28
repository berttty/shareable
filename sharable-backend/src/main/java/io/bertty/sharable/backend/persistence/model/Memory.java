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
  private Question question;
  private String Answer;
  private Date timestamp;

  private String token;
  private List<Comment> comments;

  private int i_like_it = 0;
  private int make_me_meditate = 0;
  private int so_sad = 0;

  public Memory() {
  }

  public String getId() {
    return id;
  }

  public Memory setId(String id) {
    this.id = id;
    return this;
  }

  public Question getQuestion() {
    return question;
  }

  public Memory setQuestion(
      Question question) {
    this.question = question;
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

  public String getToken() {
    return token;
  }

  public Memory setToken(String token) {
    this.token = token;
    return this;
  }

  public int getI_like_it() {
    return i_like_it;
  }
  public Memory addI_like_it() {
    this.i_like_it = this.i_like_it + 1;
    return this;
  }
  public Memory setI_like_it(int i_like_it) {
    this.i_like_it = i_like_it;
    return this;
  }

  public int getMake_me_meditate() {
    return make_me_meditate;
  }

  public Memory setMake_me_meditate(int make_meditate) {
    this.make_me_meditate = make_meditate;
    return this;
  }

  public Memory addMake_me_meditate(){
    this.make_me_meditate = this.make_me_meditate + 1;
    return this;
  }

  public int getSo_sad() {
    return so_sad;
  }

  public Memory setSo_sad(int so_sad) {
    this.so_sad = so_sad;
    return this;
  }

  public Memory addSo_sad(){
    this.so_sad = this.so_sad + 1;
    return this;
  }
}
