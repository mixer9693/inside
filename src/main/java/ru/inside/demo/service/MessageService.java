package ru.inside.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.inside.demo.dto.MessageDto;
import ru.inside.demo.entity.Message;
import ru.inside.demo.mapper.MessageMapper;
import ru.inside.demo.repository.MessageRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final MessageMapper mapper;

    public MessageDto save(MessageDto messageDto){
        Message message = mapper.dtoToMessage(messageDto);
        message = messageRepository.save(message);
        return mapper.messageToDto(message);
    }

    public List<MessageDto> getHistory(Integer limit){
        return messageRepository.findLatestLimitTo(limit)
                .stream().map(mapper::messageToDto)
                .collect(Collectors.toList());
    }

}
