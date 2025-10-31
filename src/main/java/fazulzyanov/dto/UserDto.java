package fazulzyanov.dto;

import fazulzyanov.entity.Role;

public class UserDto {
    private Integer id;
    private String name;
    private String login;
    private String imagePath;
    private String aboutInfo;
    private Role role;

    public UserDto(Integer id, String name, String login,  String imagePath, String aboutInfo, Role role) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.imagePath = imagePath;
        this.aboutInfo = aboutInfo;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getAboutInfo() {
        return aboutInfo;
    }

    public Role getRole() {
        return role;
    }
}
