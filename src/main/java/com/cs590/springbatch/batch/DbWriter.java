package com.cs590.springbatch.batch;

import com.cs590.springbatch.model.Student;
import com.cs590.springbatch.repository.StudentRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DbWriter implements ItemWriter<Student> {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void write(List<? extends Student> students) throws Exception {
        System.out.println("Data saved to database"+ students);
        studentRepository.saveAll(students);
    }
}
