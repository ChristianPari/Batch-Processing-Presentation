package com.christianpari.springbatchrunthrough1.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

  @Id
  private Integer id;
  private String name;
  private String dept;

  public User() {
  }

  public User(Integer id, String name, String dept) {
    this.id = id;
    this.name = name;
    this.dept = dept;
  }

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
}
