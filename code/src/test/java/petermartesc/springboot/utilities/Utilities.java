package petermartesc.springboot.utilities;

import petermartesc.springboot.model.Role;
import petermartesc.springboot.model.User;

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
}
