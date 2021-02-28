package com.christianpari.springbatchrunthrough1.batch;

import com.christianpari.springbatchrunthrough1.model.User;
import com.christianpari.springbatchrunthrough1.repository.UserRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Writer implements ItemWriter<User> {

  @Autowired
  private UserRepository userRepository;


  @Override
  public void write(List<? extends User> users) throws Exception {
    System.out.printf("Data Saved to Users Table: [%s]", users);
    userRepository.saveAll(users);
  }
}
