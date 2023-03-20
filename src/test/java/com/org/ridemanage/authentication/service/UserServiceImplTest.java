package com.org.ridemanage.authentication.service;

import com.org.ridemanage.authentication.exception.UserNotRegisteredException;
import com.org.ridemanage.authentication.model.Credential;
import com.org.ridemanage.authentication.repository.UserRepository;
import com.org.ridemanage.exception.InvalidInputException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeAll
    public void init() {
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void testGetRegisteredUser_WithValidCredentials_ShouldReturnUserId() {
        Credential credential = new Credential("username", "password");
        String expectedUserId = "1234";
        when(userRepository.getUser(anyString(), anyString()))
                .thenReturn(expectedUserId);
        String actualUserId = userService.getRegisteredUser(credential);
        assertEquals(expectedUserId, actualUserId);
    }

    @Test
    public void testGetRegisteredUser_WithEmptyUsername_ShouldThrowInvalidInputException() {
        Credential credential = new Credential("", "password");
        assertThrows(InvalidInputException.class, () -> {
            userService.getRegisteredUser(credential);
        });
    }

    @Test
    public void testGetRegisteredUser_WithEmptyPassword_ShouldThrowInvalidInputException() {
        Credential credential = new Credential("username", "");
        assertThrows(InvalidInputException.class, () -> {
            userService.getRegisteredUser(credential);
        });
    }

    @Test
    public void testGetRegisteredUser_WithInvalidCredentials_ShouldThrowUserNotRegisteredException() {
        Credential credential = new Credential("username", "password");
        when(userRepository.getUser(anyString(), anyString()))
                .thenReturn(null);
        assertThrows(UserNotRegisteredException.class, () -> {
            userService.getRegisteredUser(credential);
        });
    }
}
