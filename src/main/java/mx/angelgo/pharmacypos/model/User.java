package mx.angelgo.pharmacypos.model;

public class User {
    private long id;
    private String name;
    private String email;
    private String passwd;
    private String role;

    public User() {}

    // Existing user
    public User(long id, String name, String email, String passwd, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.passwd = passwd;
        this.role = role;
    }

    // New user
    public User(String name, String email, String passwd, String role) {
        this.name = name;
        this.email = email;
        this.passwd = passwd;
        this.role = role;
    }

    public long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPasswd() { return passwd; }
    public String getRole() { return role; }
}
