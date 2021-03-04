package com.careerdevs.batchprocessingwalkthrough.batch;

import com.careerdevs.batchprocessingwalkthrough.model.User;
import com.careerdevs.batchprocessingwalkthrough.repository.UserRepository;
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
    System.out.println("Data Saved to Users: " + users);
    userRepository.saveAll(users);
  }
}
