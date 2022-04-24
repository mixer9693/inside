package ru.inside.demo.service;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class HistoryHelperTest {

    private HistoryHelper historyHelper = new HistoryHelper();

    @Test
    void supportHistory() {
        String[] valid = {"history 1", "history 10"};
        String[] invalid = {" history 1", "1", "history 1s", "history 1 ", "history 1 s"};

        Stream.of(valid).forEach((e) -> {
            assertTrue(historyHelper.supportHistory(e));
        });

        Stream.of(invalid).forEach((e) -> {
            assertFalse(historyHelper.supportHistory(e));
        });
    }

    @Test
    void extractNumber() {
        assertEquals(1, historyHelper.extractNumber("history 1"));
        assertEquals(15, historyHelper.extractNumber("history 15"));
        assertEquals(-1, historyHelper.extractNumber("history -1"));
        assertThrows(NumberFormatException.class, () -> historyHelper.extractNumber("history 1s"));
        assertThrows(NullPointerException.class, () -> historyHelper.extractNumber(null));
    }
}