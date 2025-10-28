package fazulzyanov.dto;

public class UserDto {
    private String name;
    private String login;
    private String imagePath;
    private String aboutInfo;

    public UserDto(String name, String login,  String imagePath, String aboutInfo) {
        this.name = name;
        this.login = login;
        this.imagePath = imagePath;
        this.aboutInfo = aboutInfo;
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
}
