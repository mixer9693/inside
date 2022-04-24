package ru.inside.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.inside.demo.dto.MessageDto;
import ru.inside.demo.entity.Message;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "name", target = "username")
    Message dtoToMessage(MessageDto messageDto);

    @Mapping(source = "username", target = "name")
    MessageDto messageToDto(Message message);
}
