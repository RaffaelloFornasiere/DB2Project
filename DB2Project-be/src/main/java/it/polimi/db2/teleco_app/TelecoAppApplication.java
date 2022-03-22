package it.polimi.db2.teleco_app;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin
public class TelecoAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(TelecoAppApplication.class, args);
    }
}
