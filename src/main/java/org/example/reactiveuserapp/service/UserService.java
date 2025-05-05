package org.example.reactiveuserapp.service;

import org.example.reactiveuserapp.dto.UserDto;
import org.example.reactiveuserapp.entity.User;
import org.example.reactiveuserapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Mono<String> addUser(UserDto userDto) {
        User user = mapToUser(userDto);
        return userRepository.findUserByEmail(user.getEmail())
                .hasElement()
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.just("User already exists");
                    } else {
                        return userRepository.save(user)
                                .thenReturn("User saved successfully");
                    }
                })
                .onErrorResume(error -> Mono.just("Error saving user"));
    }


    public Flux<UserDto> findAllUsers() {
        return userRepository.findAll()
                .map(this::mapToUserDto);//her user'ı UserDto'ya çevirir
    }

    public Mono<String> updateUser(UserDto userDto){
        User user = mapToUser(userDto);
        return userRepository.findUserByEmail(user.getEmail())
                .flatMap(existingUser -> {
                    existingUser.setFirstName(user.getFirstName());
                    existingUser.setLastName(user.getLastName());
                    existingUser.setEmail(user.getEmail());
                    return userRepository.save(existingUser);
                })
                .thenReturn("User updated successfully")
                .onErrorResume(error -> Mono.just("Error updating user"));
    }

    public Mono<String> deleteUser(Long id) {

        return userRepository.findById(id)
                .flatMap(user -> {
                   return userRepository.delete(user);
                })
                .thenReturn("User deleted successfully")
                .onErrorResume(error -> Mono.just("Error deleting user"));

    }

    private UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
    private User mapToUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setAge(userDto.getAge());
        return user;
    }
}
