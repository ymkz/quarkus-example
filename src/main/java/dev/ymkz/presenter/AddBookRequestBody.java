package dev.ymkz.presenter;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "AddBookRequestBody")
public record AddBookRequestBody(String isbn, String title, Integer price) {}
