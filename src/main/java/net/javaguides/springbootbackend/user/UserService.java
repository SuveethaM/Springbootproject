package net.javaguides.springbootbackend.user;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
 public class UserService {
    Logger logger=LoggerFactory.getLogger(UserService.class);
     @Autowired private UserRepository repo;
     public List<User> listAll(){
         return (List<User>) repo.findAll();
     }

    public void save(User user) {
         repo.save(user);
    }
    public User get(Integer id) throws UserNotFoundExeception {
         logger.info("Started checking whether id is present in DB");
         Optional<User> result=repo.findById(id);
         if(result.isPresent()){
             logger.info("Finished checking and the ID is returned to UserController for UPDATING.");
             return result.get();

         }
        logger.error("Throwing exception because of ID not found in DB");
         throw new UserNotFoundExeception("Could not find any users ID" + id);

    }
    public void delete(Integer id) throws UserNotFoundExeception {
        logger.info("Started checking whether Employee ID is present in DB for deleting.");
         Long count = repo.countById(id);
         if(count== null || count ==0){
             logger.error("Throwing exception because of ID not present in DB");
             throw new UserNotFoundExeception("Could not find any users with ID" + id);

         }
        logger.info("Ending of checking the employee ID and returned to UserController for DELETING.");
         repo.deleteById(id);
    }
}
