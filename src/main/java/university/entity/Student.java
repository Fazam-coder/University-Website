package university.entity;

public class Student extends User {
    private String group;

    public Student(Integer id, String name, String login, String password, String imagePath, String aboutInfo, Role role) {
        super(id, name, login, password, imagePath, aboutInfo, role);
        this.group = "";
    }

    public Student(Integer id, String name, String login, String password, String imagePath, String aboutInfo) {
        super(id, name, login, password, imagePath, aboutInfo);
        this.group = "";
    }

    public Student(Integer id, String name, String login, String password) {
        super(id, name, login, password);
        this.group = "";
    }

    public Student(Integer id, String name, String login, String password, Role role) {
        super(id, name, login, password, role);
        this.group = "";
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getGroup() {
        return group;
    }
}
