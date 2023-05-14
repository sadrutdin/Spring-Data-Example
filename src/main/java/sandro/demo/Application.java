package sandro.demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sandro.demo.domain.User;
import sandro.demo.service.MessageService;
import sandro.demo.service.UserService;

@SpringBootApplication
@Slf4j
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Override
    public void run(String... args) {
        String userName = "sandro-" + RandomUtils.nextLong();
        User user = userService.create(userName);
        messageService.create("abc", user.getId());
        messageService.create("abc", user.getId());
        messageService.create("abc", user.getId());
        messageService.deleteAll(user.getId());
    }
}
