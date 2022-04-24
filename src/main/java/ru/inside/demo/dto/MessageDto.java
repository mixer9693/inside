package ru.inside.demo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MessageDto {
    @NotNull
    private String name;
    @NotNull
    private String message;
}
