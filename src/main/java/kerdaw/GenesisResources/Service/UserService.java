package kerdaw.GenesisResources.Service;

import jakarta.persistence.EntityNotFoundException;
import kerdaw.GenesisResources.dto.UserDTO;

import kerdaw.GenesisResources.Model.User;
import kerdaw.GenesisResources.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

@Service
public class UserService {

    public static final String PERSON_ID_FILE_NAME = "src/main/resources/dataPersonID.txt";
    public static final ArrayList<String> ALLOWED_PERSON_IDS = new ArrayList<>();

    @Autowired
    UserRepository userRepository;


    public User addUser(UserDTO newUser) {
        if (!isPersonIdAvailable(newUser.getPersonID())) {
            throw new UserException("Person ID is already in use");
        }
        if (!isPersonIDCertified(newUser.getPersonID())){
            throw new UserException("Person ID is not certified");
        }

        User user = new User();
        user.setName(newUser.getName());
        user.setSurname(newUser.getSurname());
        user.setPersonID(newUser.getPersonID());

        String uuid = UUID.randomUUID().toString();
        user.setUuid(uuid);

        return userRepository.save(user);
    }

    public User getUserById(Boolean detail, Integer id) {
        User foundUser;
        try {
            // nevím proč, ale getReferenceById() sám nestačí a je třeba udělat kopii,
            // jinak to vyhazuje výjimku
            foundUser = userRepository.getReferenceById(id).copy();
        } catch (EntityNotFoundException e) {
            throw new UserException("Could not find user with id " + id);
        }

        User userNoDetails = new User();
        userNoDetails.setId(foundUser.getId());
        userNoDetails.setName(foundUser.getName());
        userNoDetails.setSurname(foundUser.getSurname());

        if (detail) {
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
        User foundUser;
        try {
            foundUser = userRepository.getReferenceById(updatedUser.getId());
            foundUser.setName(updatedUser.getName());
            foundUser.setSurname(updatedUser.getSurname());
        } catch (EntityNotFoundException e) {
            throw new UserException("Could not find user with id " + updatedUser.getId());
        }
        return userRepository.save(foundUser);
    }

    public boolean deleteUser(Integer id) {
        userRepository.delete(userRepository.getReferenceById(id));
        return !userRepository.existsById(id);
    }

    private boolean isPersonIdAvailable(String personID) {
        User foundUser = userRepository.getUserByPersonID(personID);
        return foundUser == null;
    }

    private boolean isPersonIDCertified(String personID){
        return ALLOWED_PERSON_IDS.contains(personID);
    }

    @Autowired
    private void readFile() throws Exception {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(PERSON_ID_FILE_NAME)))) {
            while (scanner.hasNext()){
                ALLOWED_PERSON_IDS.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new Exception("Could not find the file " + e.getMessage());
        }
    }

}
