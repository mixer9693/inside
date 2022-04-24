package ru.inside.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ListMessageResponse implements MessageResponse {
    @JsonProperty("history")
    private List<MessageDto> messageDtoList;
}
