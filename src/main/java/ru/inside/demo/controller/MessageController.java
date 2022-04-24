package ru.inside.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.inside.demo.dto.ListMessageResponse;
import ru.inside.demo.dto.MessageDto;
import ru.inside.demo.dto.MessageResponse;
import ru.inside.demo.dto.SingleMessageResponse;
import ru.inside.demo.service.HistoryHelper;
import ru.inside.demo.service.MessageService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final HistoryHelper historyHelper;

    @PostMapping(path = "/api/messages")
    public MessageResponse saveOrGetHistory(@RequestBody @Valid MessageDto messageDto) {
        if (historyHelper.supportHistory(messageDto.getMessage())){
            Integer limit = historyHelper.extractNumber(messageDto.getMessage());
            List<MessageDto> list = messageService.getHistory(limit);
            return new ListMessageResponse(list);
        } else {
            MessageDto savedMessage = messageService.save(messageDto);
            return new SingleMessageResponse(savedMessage);
        }
    }

}
