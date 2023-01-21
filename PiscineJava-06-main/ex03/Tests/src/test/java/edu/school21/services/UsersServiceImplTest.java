package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

class UsersServiceImplTest {
    private final UsersServiceImpl usersRepositoryMock = Mockito.spy(UsersServiceImpl.class);
    private final String CORRECT_PASSWORD_FALSE = "CORRECT_LOGIN_FALSE";
    private final String CORRECT_PASSWORD_TRUE = "CORRECT_LOGIN_TRUE";
    private final String INCORRECT_PASSWORD_FALSE = "INCORRECT_PASSWORD_FALSE";
    private final String INCORRECT_LOGIN_FALSE = "INCORRECT_LOGIN_FALSE";

    @BeforeEach
    public void initMock() throws UsersRepository.EntityNotFoundException {
        Mockito.when(usersRepositoryMock.findByLogin(CORRECT_PASSWORD_TRUE)).thenReturn(new User(1l, CORRECT_PASSWORD_TRUE, CORRECT_PASSWORD_TRUE, true));
        Mockito.when(usersRepositoryMock.findByLogin(CORRECT_PASSWORD_FALSE)).thenReturn(new User(1l, CORRECT_PASSWORD_FALSE, CORRECT_PASSWORD_FALSE, false));
        Mockito.when(usersRepositoryMock.findByLogin(INCORRECT_PASSWORD_FALSE)).thenReturn(new User(1l, INCORRECT_PASSWORD_FALSE, INCORRECT_PASSWORD_FALSE, false));
        Mockito.when(usersRepositoryMock.findByLogin(INCORRECT_LOGIN_FALSE)).thenThrow(UsersRepository.EntityNotFoundException.class);
        doNothing().when(usersRepositoryMock).update(null);
        System.out.println("WWWWW");
    }

    @Test
    public void testAuthenticateCorrect() throws UsersRepository.EntityNotFoundException, AlreadyAuthenticatedException {
        assertTrue(usersRepositoryMock.authenticate(CORRECT_PASSWORD_FALSE, CORRECT_PASSWORD_FALSE));
    }

    @Test
    public void testIncorrectPass() throws AlreadyAuthenticatedException, UsersRepository.EntityNotFoundException {
        assertFalse(usersRepositoryMock.authenticate(INCORRECT_PASSWORD_FALSE, "INCORRECT"));
    }

    @Test
    public void testIncorrectAlreadyAuthenticatedException() {
        Assertions.assertThrows(AlreadyAuthenticatedException.class, () -> usersRepositoryMock.authenticate(CORRECT_PASSWORD_TRUE, CORRECT_PASSWORD_TRUE));
    }

    @Test
    public void testIncorrectEntityNotFoundException() {
        Assertions.assertThrows(UsersRepository.EntityNotFoundException.class, () -> usersRepositoryMock.authenticate(INCORRECT_LOGIN_FALSE, CORRECT_PASSWORD_TRUE));
    }



}