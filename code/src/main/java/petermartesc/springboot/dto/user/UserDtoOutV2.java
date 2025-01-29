package petermartesc.springboot.dto;

public record UserDtoOut (
        int id,
        String name,
        String password,
        int role
) {
}
