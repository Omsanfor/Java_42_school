package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;

public class UsersServiceImpl implements UsersRepository {

    boolean authenticate(String login, String password) throws AlreadyAuthenticatedException, EntityNotFoundException {
        User user = findByLogin(login);
        if (user.isAuthStatus())
            throw new AlreadyAuthenticatedException();
        else if (!user.getPassword().equals(password))
            return false;
        user.setAuthStatus(true);
        update(user);
        return true;
    }

    @Override
    public User findByLogin(String login) throws EntityNotFoundException {
        return null;
    }

    @Override
    public void update(User user) throws EntityNotFoundException {
    }
}
