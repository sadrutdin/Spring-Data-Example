package sandro.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sandro.demo.domain.User;
import sandro.demo.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional(propagation = Propagation.NEVER)
    public User create(String name) {
        User entity = new User();
        entity.setName(name);
        return userRepository.save(entity);
    }
}
