package com.cs590.springbatch.config;

import com.cs590.springbatch.model.Student;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

   @Bean
   public Job job(JobBuilderFactory jobBuilderFactory,
                  StepBuilderFactory stepBuilderFactory,
                  ItemReader<Student> itemReader,
                  ItemProcessor<Student, Student> itemProcessor,
                  ItemWriter<Student> itemWriter){

       Step step = stepBuilderFactory.get("SWA-PROJ1-FILE-LOAD")
               .<Student, Student>chunk(100)
               .reader(itemReader)
               .processor(itemProcessor)
               .writer(itemWriter)
               .build();

       return jobBuilderFactory.get("SWA-PROJ-1-LOAD")
               .incrementer(new RunIdIncrementer())
               .start(step)
               .build();
   }

   @Bean
    public FlatFileItemReader<Student> itemReader(){
       FlatFileItemReader<Student> flatFileItemReader = new FlatFileItemReader<>();
       flatFileItemReader.setResource(new ClassPathResource("students.csv"));
//       flatFileItemReader.setResource(resource);
       flatFileItemReader.setName("CSV-Reader");
       flatFileItemReader.setLinesToSkip(1);
       flatFileItemReader.setLineMapper(lineMapper());
       return flatFileItemReader;
   }

    @Bean
    public LineMapper<Student> lineMapper() {

        DefaultLineMapper<Student> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(new String[] {"first", "last", "gpa","age"});

        BeanWrapperFieldSetMapper<Student> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Student.class);

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }


}
