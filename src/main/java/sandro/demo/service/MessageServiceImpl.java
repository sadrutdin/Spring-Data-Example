package sandro.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import sandro.demo.domain.Message;
import sandro.demo.domain.User;
import sandro.demo.repository.MessageRepository;
import sandro.demo.repository.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @Override
    public Message create(String text, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "User not found"));

        Message message = new Message();
        message.setText(text);
        message.setUser(user);
        return messageRepository.save(message);
    }

    @Override
    public void deleteAll(Long userId) {
        Specification<Message> spec = (root, query, builder) -> builder.equal(root.join("user").get("id"), userId);
        List<Message> list = messageRepository.findAll(spec);
        list.forEach(that -> that.setDeleted(true));
        messageRepository.saveAll(list);
    }
}
