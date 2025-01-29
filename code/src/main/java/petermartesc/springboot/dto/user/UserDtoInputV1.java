package petermartesc.springboot.dto.user;

/**
 * Dto V1 for ADMIN to put information into DDBB
 * @param name from user
 * @param password from user
 * @param role from user
 */
public record UserDtoInputV1(
        String name,
        String password,
        int role
) {
}
