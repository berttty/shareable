package io.bertty.sharable.backend.persistence.model;


import java.util.Date;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("QRLocation")
public class QRLocation {
  public String id;

  public String name;
  public String place;
  public Date moment_intervention;

  public QRLocation() {
  }

  public String getId() {
    return id;
  }

  public QRLocation setId(String id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public QRLocation setName(String name) {
    this.name = name;
    return this;
  }

  public String getPlace() {
    return place;
  }

  public QRLocation setPlace(String place) {
    this.place = place;
    return this;
  }

  public Date getMoment_intervention() {
    return moment_intervention;
  }

  public QRLocation setMoment_intervention(Date moment_intervention) {
    this.moment_intervention = moment_intervention;
    return this;
  }
}
