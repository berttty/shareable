package io.bertty.sharable.backend.persistence.model;

import java.io.Serializable;
import java.util.Date;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Memory")
public class Comment implements Serializable {

  private String id;
  private String comment;
  private Date timestamp;

  public Comment() {
  }

  public String getId() {
    return id;
  }

  public Comment setId(String id) {
    this.id = id;
    return this;
  }

  public String getComment() {
    return comment;
  }

  public Comment setComment(String comment) {
    this.comment = comment;
    return this;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public Comment setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
    return this;
  }
}
