//package ru.itis.javalab.repositories;
//
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Repository;
//import ru.itis.javalab.models.User;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@Profile("dev")
//@Repository
//public class UsersRepositoryFakeImpl implements UsersRepository {
//
//    @Override
//    public Optional<User> findFirstByFirstnameAndLastname(String firstName, String lastName) {
//        return Optional.empty();
//    }
//
//    @Override
//    public Optional<User> findFirstByEmail(String email) {
//        return Optional.of(User.builder()
//                .firstName("FAKE")
//                .lastName("FAKEOFF")
//                .email("fakeemail@email.ru")
//                .build());
//    }
//
//    @Override
//    public void confirm(UUID confirmCode) {
//
//    }
//
//    @Override
//    public List<User> findAll() {
//        List<User> users = new ArrayList<User>();
//        users.add(User.builder()
//                .firstName("FAKE")
//                .lastName("FAKEOFF")
//                .email("fakeemail@email.ru")
//                .build());
//        return users;
//    }
//
//    @Override
//    public Optional<User> findById(Long id) {
//        return Optional.of(User.builder()
//                .firstName("FAKE")
//                .lastName("FAKEOFF")
//                .email("fakeemail@email.ru")
//                .build());
//    }
//
//    @Override
//    public void save(User entity) {
//
//    }
//
//    @Override
//    public void update(User entity) {
//
//    }
//
//    @Override
//    public void deleteById(Long id) {
//
//    }
//
//    @Override
//    public void delete(User entity) {
//
//    }
//}
//
