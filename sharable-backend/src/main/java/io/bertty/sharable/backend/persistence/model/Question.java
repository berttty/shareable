package io.bertty.sharable.backend.persistence.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Question")
public class Question {

  @Id
  private String id;

  private String question;

  public Question() {
  }

  public String getId() {
    return id;
  }

  public Question setId(String id) {
    this.id = id;
    return this;
  }

  public String getQuestion() {
    return question;
  }

  public Question setQuestion(String question) {
    this.question = question;
    return this;
  }
}
