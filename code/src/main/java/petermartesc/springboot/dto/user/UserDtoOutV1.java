package petermartesc.springboot.dto.user;

/**
 * Dto V1 for ADMIN to get information about Users.
 * @param id from user
 * @param name from user
 * @param password from user
 * @param role from user
 */
public record UserDtoOutV1(
        int id,
        String name,
        String password, // TODO: password shouldn't be showed for security
        int role
) {
}
