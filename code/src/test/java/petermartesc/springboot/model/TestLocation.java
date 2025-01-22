package petermartesc.springboot.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import petermartesc.springboot.utilities.Utilities;

public class TestLocation extends Utilities {

    Location location;

    @BeforeEach
    public void beforeEach(){
        location = new Location();
        location.setId(ID);
        location.setName(NAME);
        location.setKingdom(KINGDOM);
       
    }

    @Test
    public void getSetTest(){
        Assertions.assertEquals(ID, location.getId(), NOT_EXPECTED_RESULT);
        Assertions.assertEquals(NAME, location.getName(), NOT_EXPECTED_RESULT);
        Assertions.assertEquals(KINGDOM, location.getKingdom(), NOT_EXPECTED_RESULT);

    }

    @Test
    public void toStringTest(){
        Assertions.assertTrue(location.toString().contains(String.valueOf(ID)), NOT_EXPECTED_RESULT);
        Assertions.assertTrue(location.toString().contains(NAME), NOT_EXPECTED_RESULT);
        Assertions.assertTrue(location.toString().contains(KINGDOM), NOT_EXPECTED_RESULT);
    }

    @Test
    public void equalsTest(){
        Location equals = new Location(ID, NAME, KINGDOM);
        Location differentLocation = new Location(3, "Otro", "OTRO");
        String str = "str";
        Location nullObject = null;

        Assertions.assertEquals(location, equals, NOT_EXPECTED_RESULT);
        Assertions.assertEquals(location.hashCode(), equals.hashCode(), NOT_EXPECTED_RESULT);
        Assertions.assertNotEquals(location, differentLocation, NOT_EXPECTED_RESULT);
        Assertions.assertNotEquals(location, nullObject, NOT_EXPECTED_RESULT);
        Assertions.assertNotEquals(location, str, NOT_EXPECTED_RESULT);
    }
}
