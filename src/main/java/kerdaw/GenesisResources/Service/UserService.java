package kerdaw.GenesisResources.Service;

import jakarta.persistence.EntityNotFoundException;
import kerdaw.GenesisResources.dto.UserDTO;

import kerdaw.GenesisResources.Model.User;
import kerdaw.GenesisResources.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;

    public User addUser(UserDTO newUser){
        if (!isPersonIdAvailable(newUser.getPersonID())){
            return null;
        }

        User user = new User();
        user.setName(newUser.getName());
        user.setSurname(newUser.getSurname());
        user.setPersonID(newUser.getPersonID());

        String uuid = UUID.randomUUID().toString();
        user.setUuid(uuid);

        return userRepository.save(user);
    }

    private boolean isPersonIdAvailable(String personID){
        
        User foundUser = userRepository.getUserByPersonID(personID);
        System.out.println();
        return foundUser == null;
    }

    public User getUserById(Boolean detail, Integer id) throws JpaObjectRetrievalFailureException{

        // nevím proč, ale getReferenceById() sám nestačí a je třeba udělat kopii,
        // jinak to vyhazuje výjimku
        User foundUser = userRepository.getReferenceById(id).copy();

        User userNoDetails = new User();
        userNoDetails.setId(foundUser.getId());
        userNoDetails.setName(foundUser.getName());
        userNoDetails.setSurname(foundUser.getSurname());

        if(detail){
            return foundUser;
        }
        return userNoDetails;
    }

    public List<User> getUsers(boolean detail) {
        List<User> users = userRepository.findAll();
        if (!detail) {
            for (User u : users) {
                u.setUuid(null);
                u.setPersonID(null);
            }
        }
        return users;
    }
    
    public User updateUser(User updatedUser) {
        User foundUser = userRepository.getReferenceById(updatedUser.getId());
        foundUser.setName(updatedUser.getName());
        foundUser.setSurname(updatedUser.getSurname());
        return userRepository.save(foundUser);
    }

    public boolean deleteUser(Integer id) {
        userRepository.delete(userRepository.getReferenceById(id));
        return !userRepository.existsById(id);
    }

}
