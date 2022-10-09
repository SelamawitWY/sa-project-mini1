package com.cs590.springbatch.commandRunner;

import com.cs590.springbatch.model.Role;
import com.cs590.springbatch.model.User;
import com.cs590.springbatch.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class CommandRunner implements CommandLineRunner {
    @Autowired
    private UserRepository userRepo;

    @Override
    public void run(String... args) throws Exception {
        User user = new User("admin","123456",new ArrayList<>());
        Role admin_role = new Role("admin");
        user.setRoles(Arrays.asList(admin_role));
        userRepo.save(user);
    }
}
