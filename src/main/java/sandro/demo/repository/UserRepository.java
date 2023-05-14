package sandro.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sandro.demo.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
