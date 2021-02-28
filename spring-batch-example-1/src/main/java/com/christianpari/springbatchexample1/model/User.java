package com.christianpari.springbatchexample1.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class User {

  @Id
  private Integer id;
  private String name;
  private String dept;
  private Date time; // dont need to add

  public User(
    Integer id
    , String name
    , String dept
    , Date time // dont need to add
  ) {
    this.id = id;
    this.name = name;
    this.dept = dept;
    this.time = time; // dont need to add
  }

  public User() {}

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDept() {
    return dept;
  }

  public void setDept(String dept) {
    this.dept = dept;
  }

  public Date getTime() { // dont need to add
    return time;
  }

  public void setTime(Date time) { // dont need to add
    this.time = time;
  }

  @Override
  public String toString() {
    return "User{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", dept='" + dept + '\'' +
      '}';
  }
}
