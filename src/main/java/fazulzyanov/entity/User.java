package fazulzyanov.entity;

public class User {
    private Integer id;
    private String name;
    private String login;
    private String password;
    private String imagePath;
    private String aboutInfo;
    private Role role;

    public User(Integer id, String name, String login, String password, String imagePath, String aboutInfo, Role role) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.imagePath = imagePath;
        this.aboutInfo = aboutInfo;
        this.role = role;
    }

    public User(Integer id, String name, String login, String password, String imagePath, String aboutInfo) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.imagePath = imagePath;
        this.aboutInfo = aboutInfo;
        role = Role.USER;
    }

    public User(Integer id, String name, String login, String password) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.imagePath = "";
        this.aboutInfo = "";
        role = Role.USER;
    }

    public User(Integer id, String name, String login, String password, Role role) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.imagePath = "";
        this.aboutInfo = "";
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getAboutInfo() {
        return aboutInfo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
