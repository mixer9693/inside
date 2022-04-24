package ru.inside.demo.service;

import org.springframework.stereotype.Service;

@Service
public class HistoryHelper {
    public boolean supportHistory(String string){
        String pattern = "^history \\d+$";
        return string.matches(pattern);
    }

    public Integer extractNumber(String message){
        return Integer.parseInt(message.replace("history ", ""));
    }
}
