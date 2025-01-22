package petermartesc.springboot.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import petermartesc.springboot.utilities.Utilities;

public class TestMonster extends Utilities {

    Monster monster;

    @BeforeEach
    public void beforeEach(){
        monster = new Monster();
        monster.setId(ID);
        monster.setName(NAME);
        monster.setDifficulty(DIFFICULTY);

    }

    @Test
    public void getSetTest(){
        Assertions.assertEquals(ID, monster.getId(), NOT_EXPECTED_RESULT);
        Assertions.assertEquals(NAME, monster.getName(), NOT_EXPECTED_RESULT);
        Assertions.assertEquals(DIFFICULTY, monster.getDifficulty(), NOT_EXPECTED_RESULT);

    }

    @Test
    public void toStringTest(){
        Assertions.assertTrue(monster.toString().contains(String.valueOf(ID)), NOT_EXPECTED_RESULT);
        Assertions.assertTrue(monster.toString().contains(NAME), NOT_EXPECTED_RESULT);
        Assertions.assertTrue(monster.toString().contains(DIFFICULTY), NOT_EXPECTED_RESULT);
    }

    @Test
    public void equalsTest(){
        Monster equals = new Monster(ID, NAME, DIFFICULTY);
        Monster differentMonster = new Monster(3, "Otro", "OTRO");
        String str = "str";
        Monster nullObject = null;

        Assertions.assertEquals(monster, equals, NOT_EXPECTED_RESULT);
        Assertions.assertEquals(monster.hashCode(), equals.hashCode(), NOT_EXPECTED_RESULT);
        Assertions.assertNotEquals(monster, differentMonster, NOT_EXPECTED_RESULT);
        Assertions.assertNotEquals(monster, nullObject, NOT_EXPECTED_RESULT);
        Assertions.assertNotEquals(monster, str, NOT_EXPECTED_RESULT);
    }
}
