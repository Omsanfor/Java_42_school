public class UserIdsGenerator {
    private static UserIdsGenerator instance;
    private int id;

    private UserIdsGenerator() {
    }

    public int generateId() {
        return instance.id++;
    }

    public static UserIdsGenerator getInstance() {
        if (instance == null) {
            instance = new UserIdsGenerator();
        }
        return instance;
    }
}
