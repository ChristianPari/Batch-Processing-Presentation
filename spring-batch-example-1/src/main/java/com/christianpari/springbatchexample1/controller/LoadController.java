package com.christianpari.springbatchexample1.controller;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/load") // endpoint
public class LoadController {

  @Autowired // need to trigger the job launcher
  JobLauncher jobLauncher;

  @Autowired // needed to provide the job to the job launcher
  Job job;

  @GetMapping // simple GET to test this process
  // this method has exceptions that it can throw due to the JobLauncher Execution
  public BatchStatus load() throws
    JobParametersInvalidException
    , JobExecutionAlreadyRunningException
    , JobRestartException
    , JobInstanceAlreadyCompleteException
  {
    /*
    A Job Launcher has a run method that takes in the job that will be executed and possible parameters
      in this case we will pass in a time stamp to the Job Launcher, we do this by
        creating a Map with a String name and a JobParameter as the Key and Value
     */
    Map<String, JobParameter> maps = new HashMap<>();
    maps.put("time", new JobParameter(System.currentTimeMillis()));
    JobParameters parameters = new JobParameters(maps);
    JobExecution jobExecution = jobLauncher.run(job, parameters);
    // you can see Job information within this JobExecution class

    System.out.println("JobExecution: " + jobExecution.getStatus()); // displays the status of the Batch

    // displaying the process of th Batch
    System.out.println("Batch is Running...");
    // upon Batch completion the loop will end
    while (jobExecution.isRunning()) {
      System.out.println("...");
    }

    return jobExecution.getStatus(); // return the Batch Status which will be COMPLETED or FAILED
  }
}
