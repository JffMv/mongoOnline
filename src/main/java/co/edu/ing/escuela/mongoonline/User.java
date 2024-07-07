package co.edu.ing.escuela.mongoonline;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("usersArsw")
public class User {

    @Id
    @Setter
    @Getter
    private String id;
    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private String career;
    @Setter
    @Getter
    private String dateBorn;

    public User(String id, String name, String career, String dateBorn) {
        this.id = id;
        this.name = name;
        this.dateBorn = dateBorn;
        this.career = career;
    }



}
