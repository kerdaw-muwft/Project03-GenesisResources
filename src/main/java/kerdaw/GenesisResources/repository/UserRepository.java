package kerdaw.GenesisResources.repository;

import kerdaw.GenesisResources.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public User getUserByPersonID(String personID);
}
