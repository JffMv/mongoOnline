package co.edu.ing.escuela.mongoonline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@SpringBootApplication
@EnableMongoRepositories
public class MongoOnlineApplication implements CommandLineRunner {

    UserRepository userRepository;
    public MongoOnlineApplication( UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public static void main(String[] args) {
        SpringApplication.run(MongoOnlineApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
