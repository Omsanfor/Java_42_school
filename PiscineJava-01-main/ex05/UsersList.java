public interface UsersList {
    public void addUser(User user);
    public User getUserById(Integer id) throws UsersArrayList.UserNotFoundException;
    public User getUserByIndex(Integer index) throws UsersArrayList.UserNotFoundException;
    public Integer getSize();
}
