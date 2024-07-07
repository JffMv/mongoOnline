package co.edu.ing.escuela.mongoonline;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    @Query("{name:'?0'}")
    User findItemByName(String name);

    @Query("{id:'?0'}")
    User findItemById(String id);

    @Query(value="{career:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
    List<User> findAll(String category);
    public long count();

}
