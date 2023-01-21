package classes;

public class User {
    private String firstName;
    private String lastName;
    private int height;
    private int age;

    public User(String firstName, String lastName, int height, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.height = height;
        this.age = age;
    }

    public User() {
        this.firstName = "Default first name";
        this.lastName = "Default last name";
        this.height = 0;
        this.age = 16;
    }

    public int above(int value, int age) {
        this.height += value;
        this.age += age;
        return height;
    }

    public void older (int value) {
        this.age += value;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", height=" + height +
                ", age=" + age +
                '}';
    }
}
