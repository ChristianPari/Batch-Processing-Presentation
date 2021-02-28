package com.christianpari.springbatchexample1.config;

import com.christianpari.springbatchexample1.model.User;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

  /*
  Job Launcher will be the start of this config class
    We need to create a job
    To create a Job, we need a JobBuilderFactory
   */
  @Bean
  public Job job(
    JobBuilderFactory jobBuilderFactory // needed to create a JOB
    , StepBuilderFactory stepBuilderFactory // needed to create a STEP
    // create and say will get to them later
    , ItemReader<User> itemReader
    , ItemProcessor<User, User> itemProcessor
    , ItemWriter<User> itemWriter
  ) {

    // DO THE JOB BUILDER FIRST THEN GO THROUGH THE STEP

    Step step = stepBuilderFactory.get("ETL-file-load") // name of the STEP
      .<User, User> chunk(100) // in batches or groups or chunks of 100 records, altering can change the execution time of your Batch Process
      // need to specify the INPUT and OUTPUT data for a chunk
      // now we will create the R, P, and W
      .reader(itemReader) // first, rest of this file
      .processor(itemProcessor) // second
      .writer(itemWriter) // third
      .build();

    // EXTRACT TRANSFER LOAD PROCESS
    return jobBuilderFactory.get("ETL-Load") // the name of the JOB
              .incrementer(new RunIdIncrementer()) // IDs are assigned to Batch Runs, this auto increments each Run
              .start(step) // you can have multiple steps (use flow) if only one, use start
              .build(); // end with build
    // this returns a job for the Batch Process
  }

  // this is next
  @Bean
  public FlatFileItemReader<User> itemReader(
    @Value("${input}") Resource resource // using @Value to extract the input file from .props, make the file absolute path
  ) {
    FlatFileItemReader<User> flatFileItemReader = new FlatFileItemReader<>(); // new instance
    flatFileItemReader.setResource(resource); // set the resource to the file parameter
    flatFileItemReader.setName("CSV-Reader"); // name the READER
    flatFileItemReader.setLinesToSkip(1); // used to set the number of lines to skip at the start of a CSV file, skipping the header here
    flatFileItemReader.setLineMapper(lineMapper()); // need to map the data to the model, so we'll create a function
    return flatFileItemReader; // return the READER
  }

  @Bean
  public LineMapper<User> lineMapper() {

    DefaultLineMapper<User> defaultLineMapper = new DefaultLineMapper<>(); // first
    DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer(); // since this is a coma separated CSV file

    lineTokenizer.setDelimiter(","); // need to specify the delimiter to a comma
    lineTokenizer.setStrict(false);
    lineTokenizer.setNames("id", "name", "dept"); // provide the names of the columns, this is a String Array

    BeanWrapperFieldSetMapper<User> fieldSetMapper = new BeanWrapperFieldSetMapper<>(); // needed to set each field to the model
    fieldSetMapper.setTargetType(User.class); // automatically sets the targets of the USER class with the fields

    defaultLineMapper.setLineTokenizer(lineTokenizer); // tokenizer needs to be set back to the line mapper
    defaultLineMapper.setFieldSetMapper(fieldSetMapper); // set the field mapper to the line mapper

    return defaultLineMapper; // return the line mapper
  }

}
