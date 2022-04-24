package ru.inside.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.inside.demo.dto.MessageDto;
import ru.inside.demo.entity.Message;
import ru.inside.demo.mapper.MessageMapper;
import ru.inside.demo.repository.MessageRepository;

import java.util.List;

import static org.mockito.Mockito.*;

class MessageServiceTest {
    private MessageService messageService;
    private MessageRepository messageRepository;
    private MessageMapper mapper;

    @BeforeEach
    void init(){
        messageRepository = mock(MessageRepository.class);
        when(messageRepository.findLatestLimitTo(any())).thenReturn(getMessageList());
        mapper = mock(MessageMapper.class);
        messageService = new MessageService(messageRepository, mapper);
    }

    @Test
    void save() {
        MessageDto messageDto = getMessageDto();
        messageService.save(messageDto);
        verify(mapper, times(1)).dtoToMessage(messageDto);
        verify(messageRepository, times(1)).save(any());
        verify(mapper, times(1)).messageToDto(any());
    }

    @Test
    void getHistory() {
        Integer limit = 10;
        messageService.getHistory(limit);
        verify(messageRepository, times(1)).findLatestLimitTo(limit);
        verify(mapper, times(getMessageList().size())).messageToDto(any());
    }

    MessageDto getMessageDto(){
        MessageDto messageDto = new MessageDto();
        messageDto.setMessage("mes1");
        messageDto.setName("name1");
        return messageDto;
    }

    List<Message> getMessageList(){
        Message m1 = new Message();
        m1.setUsername("u1");
        m1.setMessage("m1");
        m1.setId(1);

        Message m2 = new Message();
        m2.setUsername("u2");
        m2.setMessage("m2");
        m2.setId(2);

        return List.of(m1, m2);
    }
}