package petermartesc.springboot.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import petermartesc.springboot.utilities.Utilities;

public class TestWeapon extends Utilities {

    Weapon weapon;

    @BeforeEach
    public void beforeEach(){
        weapon = new Weapon();
        weapon.setId(ID);
        weapon.setName(NAME);
        weapon.setRarity(RARITY);

    }

    @Test
    public void getSetTest(){
        Assertions.assertEquals(ID, weapon.getId(), NOT_EXPECTED_RESULT);
        Assertions.assertEquals(NAME, weapon.getName(), NOT_EXPECTED_RESULT);
        Assertions.assertEquals(RARITY, weapon.getRarity(), NOT_EXPECTED_RESULT);

    }

    @Test
    public void toStringTest(){
        Assertions.assertTrue(weapon.toString().contains(String.valueOf(ID)), NOT_EXPECTED_RESULT);
        Assertions.assertTrue(weapon.toString().contains(NAME), NOT_EXPECTED_RESULT);
        Assertions.assertTrue(weapon.toString().contains(RARITY), NOT_EXPECTED_RESULT);
    }

    @Test
    public void equalsTest(){
        Weapon equals = new Weapon(ID, NAME, RARITY);
        Weapon differentWeapon = new Weapon(3, "Otro", "OTRO");
        String str = "str";
        Weapon nullObject = null;

        Assertions.assertEquals(weapon, equals, NOT_EXPECTED_RESULT);
        Assertions.assertEquals(weapon.hashCode(), equals.hashCode(), NOT_EXPECTED_RESULT);
        Assertions.assertNotEquals(weapon, differentWeapon, NOT_EXPECTED_RESULT);
        Assertions.assertNotEquals(weapon, nullObject, NOT_EXPECTED_RESULT);
        Assertions.assertNotEquals(weapon, str, NOT_EXPECTED_RESULT);
    }
}
