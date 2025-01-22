package petermartesc.springboot.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import petermartesc.springboot.utilities.Utilities;

public class TestAlchemy extends Utilities {

    Alchemy alchemy;

    @BeforeEach
    public void beforeEach(){
        alchemy = new Alchemy();
        alchemy.setId(ID);
        alchemy.setName(NAME);

    }

    @Test
    public void getSetTest(){
        Assertions.assertEquals(ID, alchemy.getId(), NOT_EXPECTED_RESULT);
        Assertions.assertEquals(NAME, alchemy.getName(), NOT_EXPECTED_RESULT);
    }

    @Test
    public void toStringTest(){
        Assertions.assertTrue(alchemy.toString().contains(String.valueOf(ID)), NOT_EXPECTED_RESULT);
        Assertions.assertTrue(alchemy.toString().contains(NAME), NOT_EXPECTED_RESULT);

    }

    @Test
    public void equalsTest(){
        Alchemy equals = new Alchemy(ID, NAME, MATERIAL);
        Alchemy differentAlchemy = new Alchemy(3, "Otro", "OTRO");
        String str = "str";
        Alchemy nullObject = null;

        Assertions.assertEquals(alchemy, equals, NOT_EXPECTED_RESULT);
        Assertions.assertEquals(alchemy.hashCode(), equals.hashCode(), NOT_EXPECTED_RESULT);
        Assertions.assertNotEquals(alchemy, differentAlchemy, NOT_EXPECTED_RESULT);
        Assertions.assertNotEquals(alchemy, nullObject, NOT_EXPECTED_RESULT);
        Assertions.assertNotEquals(alchemy, str, NOT_EXPECTED_RESULT);
    }
}
