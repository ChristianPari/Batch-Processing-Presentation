package com.christianpari.springbatchexample1.batch;

import com.christianpari.springbatchexample1.model.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class Processor implements ItemProcessor<User, User> { // implements and implements methods

  private static final Map<String, String> DEPT_NAMES = new HashMap<>();

  public Processor() { // assign the values upon the Processor declaration
    DEPT_NAMES.put("001", "Technology");
    DEPT_NAMES.put("002", "Operations");
    DEPT_NAMES.put("003", "Accounting");
  }

  @Override
  public User process(User user) throws Exception { // this is where we alter the data
    // we are going to alter the department code into a specified String name of a department [CREATE THE DEPT_NAMES NOW]
    String deptCode = user.getDept();
    String dept = DEPT_NAMES.get(deptCode); // get the department name
    user.setDept(dept); // set the department to the department name
    user.setTime(new Date()); // dont need to add
    System.out.printf("Converted from [%s] to [%s]", deptCode, dept); // to see a msg from the processor at runtime
    return user; // returns the new User data
    // now we need an item writer to write the data back into the database
  }
}
