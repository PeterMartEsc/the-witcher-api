package petermartesc.springboot.dto;

public record UserDtoInputV2(
        String name,
        String password,
        int role
) {
}
