package kerdaw.GenesisResources.dto;





public class UserDTO {

    private String name;
    private String surname;
    private String personID;

    public UserDTO(String name, String surname, String personID) {
        this.name = name;
        this.surname = surname;
        this.personID = personID;
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
}
