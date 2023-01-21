import java.util.ArrayList;

public class UsersArrayList implements UsersList{
    private static final int DEFAULT_CAPACITY = 10;
    private User[] ELEMENTS_DATA;
    private int index;
    private int capacity;


    public UsersArrayList() {
        ELEMENTS_DATA = new User[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
    }

    @Override
    public void addUser(User user) {
        if (index == capacity - 1)
            arrayExpansion();
        ELEMENTS_DATA[index] = user;
         index++;
    }

    private void arrayExpansion() {
        int i = 0;
        capacity += capacity / 2;
        User[] NEW_ELEMENTS_DATA = new User[capacity];
       for (User user : ELEMENTS_DATA) {
           NEW_ELEMENTS_DATA[i] = user;
           i++;
       }
        ELEMENTS_DATA = NEW_ELEMENTS_DATA;
    }

    @Override
    public User getUserById(Integer id)  {
        for (int i = 0; i < index; i++) {
            if (ELEMENTS_DATA[i].getIdentifier() == id)
                return ELEMENTS_DATA[i];
        }
        throw new UserNotFoundException();
    }

    @Override
    public User getUserByIndex(Integer index){
        if (index > this.index - 1 || index < 0)
            throw new UserNotFoundException();
        return ELEMENTS_DATA[index];
    }

    @Override
    public Integer getSize() {
        return index;
    }


    static class UserNotFoundException extends RuntimeException {

    }
}
