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
    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(MongoOnlineApplication.class, args);
    }

    void createGroceryItems() {
        System.out.println("Data creation started...");
        userRepository.save(new User("1", "Juan", "2 de mayo", "sistemas"));
        userRepository.save(new User("2", "Man", "3 de mayo", "civil"));
        userRepository.save(new User("3", "Lola", "4 de mayo", "ambiental"));
        userRepository.save(new User("4", "Pepito", "5 de mayo", "estadistica"));
        userRepository.save(new User("5", "Felipe", "6 de mayo", "matemáticas"));
        System.out.println("Data creation complete...");
    }

    // READ
    // 1. Show all the data
    public void showAllUsers() {

        userRepository.findAll().forEach(user -> System.out.println(getUserDetails(user)));
    }

    // 2. Get item by name
    public void getUserByName(String name) {
        System.out.println("Getting user by name: " + name);
        User user = userRepository.findItemByName(name);
        System.out.println(getUserDetails(user));
    }

    // 3. Get name and quantity of a all items of a particular category
    public void getUserByDateBorn(String dateBorn) {
        System.out.println("Getting items for the date born " + dateBorn);
        List<User> list = userRepository.findAll(dateBorn);

        list.forEach(user -> System.out.println("Name: " + user.getName() + ", Career: " + user.getCareer()));
    }
    // 4. Get count of documents in the collection
    public void findCountOfUsers() {
        long count = userRepository.count();
        System.out.println("Number of users in the collection: " + count);
    }


    // Print details in readable form

    public String getUserDetails(User user
    ) {

        System.out.println(
                "User Name: " + user.getName() +
                        ", \nDate of born: " + user.getDateBorn() +
                        ", \nCarrer: " + user.getCareer()
        );

        return "";
    }
    public void updateCategoryName(String career) {

        // Change to this new value
        String newCategory = "graduado";

        // Find all the items with the category snacks
        List<User> list = userRepository.findAll(career);

        list.forEach(user -> {
            // Update the category in each document
            user.setCareer(newCategory);
        });

        // Save all the items in database
        List<User> usersUpdated = userRepository.saveAll(list);

        if(usersUpdated != null)
            System.out.println("Successfully updated " + usersUpdated.size() + " items.");}


    // DELETE
    public void deleteGroceryItem(String id) {
        userRepository.deleteById(id);
        System.out.println("User with id " + id + " deleted...");
    }

    public void run(String... args) {

        System.out.println("-----CREATE USERS-----\n");

        createGroceryItems();

        System.out.println("\n-----SHOW ALL USERS-----\n");

        showAllUsers();

        System.out.println("\n-----GET ITEM BY NAME-----\n");

        getUserByName("Pepito");

        System.out.println("\n-----GET ITEMS BY CATEGORY-----\n");

        getUserByDateBorn("2 de mayo");

        System.out.println("\n-----UPDATE CAREER NAME OF SISTEMAS-----\n");

        updateCategoryName("sistemas");

        System.out.println("\n-----DELETE A USER-----\n");

        deleteGroceryItem("Juan");

        System.out.println("\n-----FINAL COUNT OF USERS-----\n");

        findCountOfUsers();

        System.out.println("\n-----THANK YOU-----");

    }


}
