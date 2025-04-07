package kerdaw.GenesisResources.Model;

import jakarta.persistence.*;

@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String surname;
    private String personID;
    private String uuid;

    public User() {

    }

    public User(User copy){
        this.id = copy.getId();
        this.name = copy.getName();
        this.surname = copy.getSurname();
        this.personID = copy.getPersonID();
        this.uuid = copy.getUuid();
    }

    public User copy(){
        User copy = new User();
        copy.id = this.getId();
        copy.name = this.getName();
        copy.surname = this.getSurname();
        copy.personID = this.getPersonID();
        copy.uuid = this.getUuid();
        return copy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
