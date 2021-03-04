package com.careerdevs.batchprocessingwalkthrough.batch;

import com.careerdevs.batchprocessingwalkthrough.model.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Processor implements ItemProcessor<User, User> {

  private final Map<String, String> DEPT_NAMES = new HashMap<>();

  public Processor() {
    DEPT_NAMES.put("1", "Technology");
    DEPT_NAMES.put("2", "Operations");
    DEPT_NAMES.put("3", "Accounting");
  }

  @Override
  public User process(User user) throws Exception {

    String deptCode = user.getDept();
    String deptName = DEPT_NAMES.get(deptCode);
    user.setDept(deptName);
    System.out.printf("Converted from [%s] to [%s]", deptCode, deptName);

    return user;
  }
}
