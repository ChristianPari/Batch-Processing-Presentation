package com.christianpari.springbatchrunthrough1.batch;

import com.christianpari.springbatchrunthrough1.model.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Processor implements ItemProcessor<User, User> {
  private final Map<String, String> DEPT_NAMES = new HashMap<>();

  public Processor() {
    DEPT_NAMES.put("001", "Technology");
    DEPT_NAMES.put("002", "Operations");
    DEPT_NAMES.put("003", "Accounting");
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
