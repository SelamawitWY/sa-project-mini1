package com.cs590.springbatch.batch;

import com.cs590.springbatch.model.Student;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
public class BatchProcessor implements ItemProcessor<Student, Student> {

    @Override
    public Student process(Student student) throws Exception {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate date = LocalDate.now().minusYears(student.getAge().intValue()).withDayOfMonth(1).withDayOfYear(1);
        Date dob = Date.from(date.atStartOfDay(defaultZoneId).toInstant());
        student.setDOB(dob);
        return student;
    }
}
