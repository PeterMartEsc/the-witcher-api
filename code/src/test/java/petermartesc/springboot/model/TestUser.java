package petermartesc.springboot.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import petermartesc.springboot.utilities.Utilities;

public class TestUser extends Utilities {

    User user;

    @BeforeEach
    public void beforeEach(){
        user = new User();
        user.setId(ID);
        user.setName(NAME);
        user.setPassword(PASSWORD);
        user.setRole(ROLE);
    }

    @Test
    public void getSetTest(){
        Assertions.assertEquals(ID, user.getId(), NOT_EXPECTED_RESULT);
        Assertions.assertEquals(NAME, user.getName(), NOT_EXPECTED_RESULT);
        Assertions.assertEquals(PASSWORD, user.getPassword(), NOT_EXPECTED_RESULT);
        Assertions.assertEquals(ROLE, user.getRole(), NOT_EXPECTED_RESULT);
    }

    @Test
    public void toStringTest(){
        Assertions.assertTrue(user.toString().contains(String.valueOf(ID)), NOT_EXPECTED_RESULT);
        Assertions.assertTrue(user.toString().contains(NAME), NOT_EXPECTED_RESULT);
        Assertions.assertTrue(user.toString().contains(PASSWORD), NOT_EXPECTED_RESULT);
        Assertions.assertTrue(user.toString().contains(ROLE.getRoleName()), NOT_EXPECTED_RESULT);
    }

    @Test
    public void equalsTest(){
        User equals = new User(ID, NAME, ROLE);
        User differentRole = new User(NAME, PASSWORD, ROLE);
        User completelyDifferent = new User(NAME, PASSWORD, ROLE);
        String str = "str";
        User nullObject = null;

        Assertions.assertEquals(user, equals, NOT_EXPECTED_RESULT);
        Assertions.assertEquals(user.hashCode(), equals.hashCode(), NOT_EXPECTED_RESULT);
        Assertions.assertNotEquals(user, differentRole, NOT_EXPECTED_RESULT);
        Assertions.assertNotEquals(user, completelyDifferent, NOT_EXPECTED_RESULT);
        Assertions.assertNotEquals(user, nullObject, NOT_EXPECTED_RESULT);
        Assertions.assertNotEquals(user, str, NOT_EXPECTED_RESULT);
    }
}
