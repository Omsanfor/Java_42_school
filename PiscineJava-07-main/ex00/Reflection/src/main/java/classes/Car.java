package classes;

public class Car {
    private String type;
    private String model;
    private String color;
    int speed;

    public Car(String type, String model, String color, int speed) {
        this.type = type;
        this.model = model;
        this.color = color;
        this.speed = speed;
    }

    public Car() {
        this.type = "Ford";
        this.model = "Focus";
        this.color = "red";
        this.speed = 10;
    }

    public void repaint(String color) {
        this.color = color;
    }

    public int upSpeed(int count) {
        this.speed += count;
        return this.speed;
    }

    @Override
    public String toString() {
        return "Car{" +
                "type='" + type + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", speed=" + speed +
                '}';
    }
}
