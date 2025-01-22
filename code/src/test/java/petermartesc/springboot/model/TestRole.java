package petermartesc.springboot.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import petermartesc.springboot.utilities.Utilities;

public class TestRole extends Utilities {

    Role role;

    @BeforeEach
    public void beforeEach(){
        role = new Role();
        role.setId(ID);
        role.setRoleName(NAME);
        
    }

    @Test
    public void getSetTest(){
        Assertions.assertEquals(ID, role.getId(), NOT_EXPECTED_RESULT);
        Assertions.assertEquals(NAME, role.getRoleName(), NOT_EXPECTED_RESULT);
    }

    @Test
    public void toStringTest(){
        Assertions.assertTrue(role.toString().contains(String.valueOf(ID)), NOT_EXPECTED_RESULT);
        Assertions.assertTrue(role.toString().contains(NAME), NOT_EXPECTED_RESULT);

    }

    @Test
    public void equalsTest(){
        Role equals = new Role(ID, NAME);
        Role differentRole = new Role(3, "Otro");
        String str = "str";
        Role nullObject = null;

        Assertions.assertEquals(role, equals, NOT_EXPECTED_RESULT);
        Assertions.assertEquals(role.hashCode(), equals.hashCode(), NOT_EXPECTED_RESULT);
        Assertions.assertNotEquals(role, differentRole, NOT_EXPECTED_RESULT);
        Assertions.assertNotEquals(role, nullObject, NOT_EXPECTED_RESULT);
        Assertions.assertNotEquals(role, str, NOT_EXPECTED_RESULT);
    }
}
