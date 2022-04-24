package ru.inside.demo.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class SingleMessageResponse implements MessageResponse {
    @JsonUnwrapped
    private MessageDto messageDto;
}
