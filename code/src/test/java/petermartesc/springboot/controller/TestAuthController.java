package petermartesc.springboot.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import petermartesc.springboot.dto.user.UserDtoAuth;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.security.AuthService;
import petermartesc.springboot.utilities.Utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TestAuthController extends Utilities {
    
    @Mock
    AuthService mockAuthService;

    @InjectMocks
    AuthRESTController controller;

    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        controller = new AuthRESTController();
        controller.setAuthService(mockAuthService);
    }

    @Test
    void loginTest() {
        when(mockAuthService.authenticate(NAME, PASSWORD)).thenReturn("token_example");
        UserDtoAuth dto = new UserDtoAuth(NAME, PASSWORD);
        Assertions.assertEquals("token_example", controller.login(dto), NOT_EXPECTED_RESULT);
    }

    @Test
    void registerTest() throws ResourceNotFoundException {
        when(mockAuthService.register(NAME, PASSWORD)).thenReturn("token_example");
        UserDtoAuth dto = new UserDtoAuth(NAME, PASSWORD);
        Assertions.assertEquals("token_example", controller.register(dto), NOT_EXPECTED_RESULT);
    }

    @Test
    void loginInvalidrTest() throws ResourceNotFoundException {
        when(mockAuthService.register(NAME, PASSWORD)).thenReturn(null);
        UserDtoAuth dto = new UserDtoAuth(NAME, PASSWORD);
        RuntimeException runtimeException = new RuntimeException("Credenciales inválidas");
        Exception exception = assertThrows(RuntimeException.class, () -> controller.login(dto));

        String expectedMessage = "Credenciales inválidas";
        String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage, NOT_EXPECTED_RESULT);
    }
    
}
