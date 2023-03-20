package com.org.ridemanage.authentication.controller;

import com.org.ridemanage.authentication.model.Credential;
import com.org.ridemanage.authentication.model.User;
import com.org.ridemanage.authentication.service.TokenService;
import com.org.ridemanage.authentication.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private TokenService tokenService;

    @InjectMocks
    private UserController userController;

    @Test
    void testSignupUser() {
        User user = new User();
        ResponseEntity<String> expectedResponse = new ResponseEntity<>(HttpStatus.OK);

        ResponseEntity<String> response = userController.signupUser(user);

        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
        verify(userService, times(1)).signupUser(user);
    }

    @Test
    void testLogin() {
        Credential credential = new Credential();
        String userId = "123";
        String token = "abc";
        when(userService.getRegisteredUser(any())).thenReturn(userId);
        when(tokenService.generateToken(userId)).thenReturn(token);
        ResponseEntity<String> expectedResponse = new ResponseEntity<>(token, HttpStatus.OK);

        ResponseEntity<String> response = userController.login(credential);

        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
        assertEquals(expectedResponse.getBody(), response.getBody());
        verify(userService, times(1)).getRegisteredUser(credential);
        verify(tokenService, times(1)).generateToken(userId);
    }
}
