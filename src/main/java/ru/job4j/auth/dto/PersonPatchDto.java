package ru.job4j.auth.dto;


import jakarta.validation.constraints.NotBlank;

public record PersonPatchDto(
        @NotBlank(message = "Password must not be empty")
        String password
) {
}
