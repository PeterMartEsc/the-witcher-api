package petermartesc.springboot.dto.user;

/**
 * Dto V2 for USER to put information into DDBB
 * @param name
 * @param password
 */
public record UserDtoInputV2(
        String name,
        String password
        //int role
) {
}
