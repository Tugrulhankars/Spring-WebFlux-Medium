package org.example.reactiveuserapp.repository;

import org.example.reactiveuserapp.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User,Long> {

    Mono<User> findUserByEmail(String email);
}
