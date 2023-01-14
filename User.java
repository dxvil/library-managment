import java.util.UUID;

public class User {
    String name;
    UUID id;
    Role role;

    User(String name, Role role) {
        this.name = name;
        this.role = role;
        Identifier identifier = new Identifier();
        this.id = identifier.generateUuid();
    }

    void changeRole(Role newRole) {
        if(role == Role.ADMIN) {
            this.role = newRole;
            return;
        }
        System.out.println("You don't have an access to change your rights.");
    }

    public String toString(){
       return "Username is " + name +" role is " + role;
    }
}