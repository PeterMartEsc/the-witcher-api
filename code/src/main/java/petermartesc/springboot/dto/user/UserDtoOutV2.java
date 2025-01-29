package petermartesc.springboot.dto.user;

/**
 * Dto V2 for USER to get information about User
 * @param id from user
 * @param name from user
 * @param role from user
 */
public record UserDtoOutV2(
        int id,
        String name,
        int role
) {
}
