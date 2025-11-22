package mx.angelgo.pharmacypos.model;

public class User {
    private long id;
    private String name;
    private String email;
    private String passwd;
    private String role;
    private String noEmployee;
    private String accessCode;
    private String secretWord;

    public User() {}

    // Existing user
    public User(long id, String name, String email, String passwd, String role, String noEmployee, String accessCode, String secretWord) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.passwd = passwd;
        this.role = role;
        this.noEmployee = noEmployee;
        this.accessCode = accessCode;
        this.secretWord = secretWord;
    }

    // New user
    public User(String name, String email, String passwd, String role, String noEmployee, String accessCode, String secretWord) {
        this.name = name;
        this.email = email;
        this.passwd = passwd;
        this.role = role;
        this.noEmployee = noEmployee;
        this.accessCode = accessCode;
        this.secretWord = secretWord;
    }

    public long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPasswd() { return passwd; }
    public String getRole() { return role; }
    public String getNoEmployee() { return noEmployee; }
    public String getAccessCode() { return accessCode; }
    public String getSecretWord() { return secretWord; }
}
