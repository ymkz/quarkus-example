package dev.ymkz.domain.model;

import dev.ymkz.domain.value.Isbn13;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record Book(@NotNull Isbn13 isbn, @NotBlank String title, @PositiveOrZero int price) {}
