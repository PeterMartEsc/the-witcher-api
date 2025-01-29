package petermartesc.springboot.dto;

public record UserDtoOutV2(
        int id,
        String name,
        String password,
        int role
) {
}
