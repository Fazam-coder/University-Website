package university.dto;

import university.entity.Role;

public class StudentDto extends UserDto {
    private String group;

    public StudentDto(Integer id, String name, String login, String imagePath, String aboutInfo, Role role) {
        super(id, name, login, imagePath, aboutInfo, role);
        this.group = "";
    }

    public StudentDto(Integer id, String name, String login, String imagePath, String aboutInfo, Role role, String group) {
        super(id, name, login, imagePath, aboutInfo, role);
        this.group = group;
    }

    public String getGroup() {
        return group;
    }

}
