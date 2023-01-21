package edu.school21.repositories;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.models.User;

public interface UsersRepository {
    User findByLogin(String login) throws EntityNotFoundException;
    void update(User user) throws EntityNotFoundException;

    public class EntityNotFoundException extends Exception{
        @Override
        public String getMessage() {
            return super.getMessage();
        }
    }
}
