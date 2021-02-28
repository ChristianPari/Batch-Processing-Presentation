package com.christianpari.springbatchexample1.batch;

import com.christianpari.springbatchexample1.model.User;
import com.christianpari.springbatchexample1.repository.UserRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Writer implements ItemWriter<User> { // this will be used to write the data into the database

  @Autowired // need to auto wire the userRepository we created
  private UserRepository userRepository;

  @Override
  public void write(List<? extends User> users) throws Exception {
    // within this write, we are just going to save all the user into the database
    System.out.println("Data Saved to Users: " + users); // simple output to the console to show us saving the data to the database
    userRepository.saveAll(users);
  }
}
