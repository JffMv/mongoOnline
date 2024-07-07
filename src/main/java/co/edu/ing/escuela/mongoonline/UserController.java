package co.edu.ing.escuela.mongoonline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

@RestController
public class UserController {
    UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(
            value = "/users",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> allUsers() {
        List<User> users = userService.getAllUsers();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonUsers = objectMapper.writeValueAsString(users);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(jsonUsers);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error converting users to JSON");
        }
    }
    @RequestMapping(
            value = "/user-add",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> addUser(@RequestBody User user) {
        try {
            userService.createUser(user);
            return ResponseEntity.ok().body("{\"message\": \"Save successful\"}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"message\": \"Not saved\"}");
        }
    }

    @RequestMapping(
            value = "/user-delete",
            method = RequestMethod.POST
    )
    public  ResponseEntity<String> deleteUser(@RequestBody String id){
        try{
            userService.deleteUser(id);
            return ResponseEntity.ok().body("{\"message\": \"Delete successful\"}");
        }catch (Exception e){
            return ResponseEntity.ok().body("{\"message\": \"Not delete\"}");

        }
    }

    @RequestMapping(
            value = "/user-update",
            method = RequestMethod.POST
    )
    public  ResponseEntity<String> updateUser(@RequestBody User user){
        try{
            userService.deleteUser(user.getId());
            userService.createUser(user);
            return ResponseEntity.ok().body("{\"message\": \"Update successful\"}");
        }catch (Exception e){
            return ResponseEntity.ok().body("{\"message\": \"Not update\"}");

        }
    }

}
