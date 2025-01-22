package petermartesc.springboot.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import petermartesc.springboot.utilities.Utilities;

public class TestCharacter extends Utilities {

    Character character;

    @BeforeEach
    public void beforeEach(){
        character = new Character();
        character.setId(ID);
        character.setName(NAME);
        character.setSurname(SURNAME);
        character.setDescription(DESCRIPTION);
    }

    @Test
    public void getSetTest(){
        Assertions.assertEquals(ID, character.getId(), NOT_EXPECTED_RESULT);
        Assertions.assertEquals(NAME, character.getName(), NOT_EXPECTED_RESULT);
        Assertions.assertEquals(SURNAME, character.getSurname(), NOT_EXPECTED_RESULT);
        Assertions.assertEquals(DESCRIPTION, character.getDescription(), NOT_EXPECTED_RESULT);
    }

    @Test
    public void toStringTest(){
        Assertions.assertTrue(character.toString().contains(String.valueOf(ID)), NOT_EXPECTED_RESULT);
        Assertions.assertTrue(character.toString().contains(NAME), NOT_EXPECTED_RESULT);
        Assertions.assertTrue(character.toString().contains(SURNAME), NOT_EXPECTED_RESULT);
        Assertions.assertTrue(character.toString().contains(DESCRIPTION), NOT_EXPECTED_RESULT);
    }

    @Test
    public void equalsTest(){
        Character equals = new Character(ID, NAME, SURNAME, DESCRIPTION);
        Character differentCharacter = new Character(3, "Otro", "OTRO", "Otra descripcion");
        String str = "str";
        Character nullObject = null;

        Assertions.assertEquals(character, equals, NOT_EXPECTED_RESULT);
        Assertions.assertEquals(character.hashCode(), equals.hashCode(), NOT_EXPECTED_RESULT);
        Assertions.assertNotEquals(character, differentCharacter, NOT_EXPECTED_RESULT);
        Assertions.assertNotEquals(character, nullObject, NOT_EXPECTED_RESULT);
        Assertions.assertNotEquals(character, str, NOT_EXPECTED_RESULT);
    }
}
