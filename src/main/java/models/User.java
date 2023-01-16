package models;
import java.util.UUID;

public class User {
    public String name;
    public UUID id;
    public Role role;

    public User(String name, Role role) {
        this.name = name;
        this.role = role;
        setUuid();
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public UUID getUuid() {
        return id;
    }

    private void setUuid() {
        Identifier identifier = new Identifier();
        this.id = identifier.generateUuid();
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role newRole) {
        this.role = newRole;
    }
    
    void changeRole(Role newRole) {
        if(role == Role.ADMIN) {
            setRole(newRole);
            return;
        }
        System.out.println("You don't have an access to change your rights.");
    }

    public String toString(){
       return "Username is " + name +" role is " + role;
    }
}