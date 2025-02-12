package petermartesc.springboot.utilities;

import petermartesc.springboot.model.*;
import petermartesc.springboot.model.Character;

public class Utilities {
    public final String NOT_EXPECTED_RESULT = "NO SE HA OBTENIDO EL RESULTADO ESPERADO";

    public static final int ID = 1;

    public static final String NAME = "nameTest";
    public static final String PASSWORD = "password_example";
    public static final String SURNAME = "surnameTest";
    public static final String DESCRIPTION = "descriptionTest";
    public static final String MATERIAL = "materialName";
    public static final String KINGDOM = "kingdomName";
    public static final String DIFFICULTY = "kingdomName";
    public static final String RARITY = "rarityExample";


    public static final Role ROLE = new Role(1, "ROLE_ADMIN");

    public static final User USER = new User(NAME, PASSWORD,ROLE);

    public static final Alchemy ALCHEMY = new Alchemy(3, NAME, MATERIAL);
    public static final Character CHARACTER = new Character(3, NAME, SURNAME, DESCRIPTION);
    public static final Location LOCATION = new Location(3, NAME, KINGDOM);

    public static final Monster MONSTER = new Monster(3, NAME, DIFFICULTY);

    public static final Weapon WEAPON = new Weapon(3, NAME, RARITY);
}
