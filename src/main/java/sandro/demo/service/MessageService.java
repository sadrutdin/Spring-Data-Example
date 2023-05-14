package sandro.demo.service;

import sandro.demo.domain.Message;

public interface MessageService {
    Message create(String text, Long userId);

    void deleteAll(Long userId);
}
